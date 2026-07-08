package org.nasdanika.groovy.dsl

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.InternalEObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil

/**
 * Metamodel-driven context for the reflective DSL. This is the {@code ProductManagementGroovyDsl}
 * plumbing (factory, id index, deferred resolution, cross-file {@code ref()}) generalised so that
 * <em>nothing</em> is hard-coded per {@link EClass} or per feature: every fact the builder needs is
 * read from the {@link EPackage} at construction time.
 *
 * <p>Drop-in starting point: it lives alongside the hand-written builders rather than replacing them.
 * See groovy-kotlin-dsl-design.md &sect;5.6.</p>
 */
class DslContext {

	interface Resolver {
		
        EClass classByName(EObject base, String name)
        EClassifier classifierByName(EObject base, String name)
		EClass classByInstanceClass(EObject base, Class clazz)
		List<EClass> candidates(EObject base, EClass featureType, EClass targetType)
				
		/**
		 * Returns a map of decapitalised EClass names to concrete EClasses in the metamodel.
		 * @return
		 */
		Map<String, EClass> names();

        default Map<String, Object> bindings() {
            return [:]
        }
		
		/**
		 * Registers a global object by id, so that it can be referenced from other resources.
		 * @param id
		 * @param element
		 */
		void global(Object id, EObject element);
		
		/**
		 * Finds an object by id (URI).
		 * @param id
		 * @return
		 */
		EObject get(String id);

		/**
		 * Records the source location an object or feature value was authored at. Called during loading
		 * through {@link DslContext#mark(EObject, EStructuralFeature)}: with {@code feature == null} when
		 * an {@link EObject} is created, and with the feature when a feature value is set on it.
		 *
		 * <p>{@code line} is the 1-based line of the offending statement in the {@code .groovy} script,
		 * recovered from the Groovy runtime stack (or {@code -1} when unknown). {@code col} is always
		 * {@code -1}: the Groovy runtime does not expose columns in stack traces (see
		 * {@link DslContext#located(Throwable)}); the parameter is kept for a future AST-based
		 * implementation that could supply it.</p>
		 *
		 * <p>The default is a no-op; override to associate line/column information with loaded objects
		 * (e.g. for hyperlinking diagnostics or navigation back to source).</p>
		 *
		 * @param eObject the object being created, or the object carrying the feature being set
		 * @param feature the feature being set, or {@code null} when {@code eObject} itself was created
		 * @param line 1-based script line, or {@code -1} when unknown
		 * @param col always {@code -1} (columns are not exposed by the Groovy runtime)
		 */
		default void mark(EObject eObject, EStructuralFeature feature, int line, int col) {
			// no-op by default
		}

	}
	
    final Resolver resolver
    final Resource resource
    final ResourceSet resourceSet
    final URI baseURI
	
    /**
     * Reference resolvers run after the script has been evaluated (so every id is indexed). Each is
     * paired with the script line it was registered from ({@code [line, closure]}); the script frame
     * is no longer on the stack when these run, so the line is captured here for diagnostics.
     */
    private final List<Object[]> deferred = []
    /** Top-level elements created by root entry points; the terminal stage adds them to the resource. */
    private final List<EObject> roots = []
    /** Cache for {@link #referenceWrapperFor}: 'featureType->targetType' -> wrapper EClass (or NONE). */
    private final Map<String, EClass> wrapperCache = [:]
    private static final EClass NONE = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass()

    /**
     * Save callback registered by the script through {@code onSave { outputStream, options -> ... }}.
     * When present it takes over persistence (see {@link #save(OutputStream, Map)}); when absent the
     * handler falls back to the read-only default (throwing {@code UnsupportedOperationException}).
     */
    private Closure saveHandler

    DslContext(Resolver resolver, Resource resource) {
        this.resolver = resolver
        this.resource = resource
        this.resourceSet = resource?.resourceSet
        this.baseURI = resource?.URI
    }

    // --- factory / registry ---------------------------------------------------------------------

    EObject create(EClass type) {
        (EObject) type.EPackage.EFactoryInstance.create(type)
    }

    /**
     * Register an element as a global object under the given id (URI), so it can be referenced from
     * other resources. Backs the {@code global '<uri>'} keyword in {@link ReflectiveBuilder}.
     */
    void global(Object id, EObject element) {
        resolver.global(id, element)
    }

    /** Concrete EClass registered under a decapitalised name, or {@code null}. */
    EClass classByName(EObject base, String name) {
        resolver.classByName(base, name)
    }

    /** Coerce a type token (EClass, Class, or name String) to an EClass, or {@code null}. */
    EClass toEClass(EObject base, Object token) {
        if (token instanceof EClass) {
            return (EClass) token
        }
        if (token instanceof Class) {
		    return resolver.classByInstanceClass(base, (Class) token)
        }
        if (token instanceof CharSequence) {
            String s = token.toString()
			return resolver.classByName(base, s);
        }
        null
    }

    /**
     * Resolve a reference selector relative to {@code base}. A selector is one of:
     *
     * <ul>
     *   <li>an {@link EObject} - returned as-is (already resolved);</li>
     *   <li>a {@link Closure} - cloned, given {@code base} as its delegate (DELEGATE_FIRST) and as
     *       its single argument, then invoked; whatever it returns is resolved recursively, so a
     *       closure may yield either an {@link EObject} or a path/URI {@link String}. This lets
     *       authors navigate the model directly, e.g.
     *       {@code { eContainer().eContainer().getEClassifier('Person') }};</li>
     *   <li>a {@link CharSequence} - a relative path / cross-resource URI, resolved as described in
     *       {@link #resolveRelativePath(EObject, String)}.</li>
     * </ul>
     */
    EObject resolveRelative(EObject base, Object selector) {
        if (selector == null) {
            return null
        }
        if (selector instanceof EObject) {
            return (EObject) selector
        }
        if (selector instanceof Closure) {
            Closure copy = (Closure) ((Closure) selector).clone()
            copy.delegate = base
            copy.resolveStrategy = Closure.DELEGATE_FIRST
            Object result = copy.call(base)
            return resolveRelative(base, result)            // result may be EObject or path String
        }
        if (selector instanceof CharSequence) {
            return resolveRelativePath(base, selector.toString())
        }
        throw new IllegalArgumentException(
            "Cannot use ${selector.getClass().name} as a reference selector")
    }

    /**
     * Resolve a string reference expression relative to {@code base}.
     *
     * <ul>
     *   <li>contains {@code '#'} - a (possibly relative) cross-resource URI, resolved through the
     *       resource set (this is where global objects and proxies live);</li>
     *   <li>leading {@code '/'} - anchored at the resource root container of {@code base};</li>
     *   <li>otherwise - relative to {@code base} itself.</li>
     * </ul>
     *
     * <p>Each forward path step is a standard EMF URI-fragment segment - {@code @feature},
     * {@code @feature.index} or {@code @feature[key='value']} (eKeys) - resolved by EMF's own
     * {@link InternalEObject#eObjectForURIFragmentSegment}, so eKey/index/single-valued matching,
     * value decoding and escaping all come for free. The leading {@code '@'} is optional in author
     * input (prepended here); EMF requires predicate values to be quoted, e.g.
     * {@code personas[id='pavel']}. A {@code '..'} step navigates to {@link EObject#eContainer}.</p>
     *
     * <p>Note: resolution matches the named attribute and does <em>not</em> require eKeys to be
     * declared on the reference; eKeys are only needed when EMF must <em>emit</em> this fragment
     * form (proxy URIs / serialization).</p>
     */
    EObject resolveRelativePath(EObject base, String expr) {
		if (!expr.contains('/')) {
			EClassifier eClassifier = resolver.classifierByName(base, expr)
			if (eClassifier != null) {
                return eClassifier
            }
		}
			
        if (expr.contains('#')) {
            if (resourceSet == null) {
                return null
            }
            URI uri = URI.createURI(expr)
            if (baseURI != null) {
                uri = uri.resolve(baseURI)
            }
            return resourceSet.getEObject(uri, true)        // may yield a proxy, resolved lazily
        }

        EObject cur
        if (expr.startsWith('/')) {
            cur = base != null ? EcoreUtil.getRootContainer(base) : (resource != null && !resource.contents.empty ? resource.contents[0] : null)
        } else {
            cur = base
        }
        if (cur == null) {
            throw new IllegalStateException("Cannot resolve relative reference '${expr}' without a context object")
        }
		
        for (String seg : expr.tokenize('/')) {
            seg = seg.trim()
            if (!seg) {
                continue
            }
            if (seg == '..') {
                cur = cur.eContainer()
                if (cur == null) {
                    throw new IllegalStateException("'..' steps past the resource root in '${expr}'")
                }
                continue
            }
			
			// ./<name> form to disambiguater from resolver classifiers.
            if (seg == '.') {
                continue
            }
            if (!seg.startsWith('@')) {
                seg = '@' + seg                             // accept terse 'personas[...]'
            }
            cur = ((InternalEObject) cur).eObjectForURIFragmentSegment(seg)
            if (cur == null) {
                throw new IllegalStateException("Cannot resolve segment '${seg}' in '${expr}'")
            }
        }
        cur
    }

    /**
     * Explicit reference helper. The {@code selector} may be a path/URI String
     * ({@code ref('../personas[id=\'pavel\']')}, {@code ref('a.pm#//@x')}), an {@link EObject}, or a
     * {@link Closure} that computes one - see {@link #resolveRelative(EObject, Object)}.
     */
    EObject ref(Object selector) {
        EObject resolved = resolveRelative(null, selector)
        if (resolved == null) {
            throw new IllegalStateException("Cannot resolve reference '" + selector + "'")
        }
        resolved
    }

    void defer(Closure resolver) {
        deferred << ([scriptLine(new Throwable()), resolver] as Object[])
    }

    // --- diagnostics ----------------------------------------------------------------------------

    /**
     * Groovy infrastructure that authors a {@code .groovy} stack frame but isn't the user's script:
     * the Groovy runtime/engine and this DSL's own builder classes (all compiled from Groovy). A
     * {@code .groovy} frame outside these prefixes belongs to the script being loaded.
     */
    private static final List<String> LIB_PREFIXES = [
        'org.nasdanika.', 'groovy.', 'org.codehaus.groovy.', 'org.apache.groovy.'
    ]

    /**
     * Tag a build/evaluation failure with the source location it came from. The original throw site
     * lives in the deepest cause, so the cause chain is scanned deepest-first for the first script
     * frame (line recovered from the Groovy stack trace; column is not exposed by the runtime, so it
     * stays {@code -1}); the root cause supplies the message. Already-located exceptions are returned
     * unchanged so wrapping is idempotent.
     */
    DslException located(Throwable t) {
        if (t instanceof DslException) {
            return (DslException) t
        }
        List<Throwable> chain = []
        for (Throwable cur = t; cur != null && !chain.contains(cur); cur = cur.cause) {
            chain << cur
        }
        int line = -1
        for (Throwable cur : chain.reverse()) {     // deepest cause first - that's the throw site
            int l = scriptLine(cur)
            if (l > 0) {
                line = l
                break
            }
        }
        Throwable root = chain.last()
        String message = root.message ?: root.toString()
        new DslException(message, t, baseURI, line, -1)
    }

    /**
     * Records the source location of a just-created object ({@code feature == null}) or a just-set
     * feature value, by recovering the authoring script line from the current stack - the same
     * mechanism {@link #located(Throwable)} uses for diagnostics - and forwarding it to
     * {@link Resolver#mark}. Column is not exposed by the Groovy runtime, so it is reported as
     * {@code -1}. Invoked from {@link ReflectiveBuilder} at the object-creation and feature-setting
     * points, while the user's script frame is still on the stack.
     */
    void mark(EObject eObject, EStructuralFeature feature) {
        resolver.mark(eObject, feature, scriptLine(new Throwable()), -1)
    }

    /** First user-script ({@code .groovy}, non-library) line in a throwable's stack trace, or {@code -1}. */
    private static int scriptLine(Throwable t) {
        for (StackTraceElement e : t.stackTrace) {
            String fn = e.fileName
            if (fn == null || !fn.endsWith('.groovy')) {
                continue
            }
            String cn = e.className
            if (cn != null && LIB_PREFIXES.any { cn.startsWith(it) }) {
                continue
            }
            if (e.lineNumber > 0) {
                return e.lineNumber
            }
        }
        -1
    }

    /** Called by the terminal normalise stage, after the script has been evaluated. */
    void resolveDeferred() {
        for (Object[] entry : deferred) {
            int line = (int) entry[0]
            Closure cl = (Closure) entry[1]
            try {
                cl.call()
            } catch (DslException e) {
                throw e
            } catch (RuntimeException e) {
                // No script frame on the stack now; use the line captured when this was deferred.
                throw new DslException(e.message ?: e.toString(), e, baseURI, line, -1)
            }
        }
    }

    List<EObject> getRoots() {
        roots
    }

    // --- save support ---------------------------------------------------------------------------

    /**
     * Registers a save callback backing the {@code onSave { source, outputStream, options -> ... }}
     * keyword - a self-writing script. The callback is invoked on save (see
     * {@link #save(String, OutputStream, Map)}) with three arguments:
     *
     * <ul>
     *   <li>{@code source} - the original {@code .groovy} script text this resource was loaded from,
     *       so the callback can write it back verbatim, or emit a modified version (e.g. append DSL
     *       fragments for newly discovered/modified objects);</li>
     *   <li>{@code outputStream} - the target the script content is written to;</li>
     *   <li>{@code options} - the save options {@link Map}.</li>
     * </ul>
     *
     * <p>The real effect of a save may live entirely in side effects (calling Jira APIs, updating a
     * database); in that case the callback does its work and writes {@code source} back unchanged so
     * the {@code .groovy} file is preserved. Registering a callback replaces any previously registered
     * one - the last {@code onSave} wins.</p>
     *
     * <p>Note: the output stream is opened against the resource URI (the {@code .groovy} source itself)
     * before this callback runs, so a callback that writes nothing leaves the source file truncated -
     * write {@code source} back to preserve it.</p>
     */
    void onSave(Closure callback) {
        this.saveHandler = callback
    }

    /** Whether a script registered an {@link #onSave(Closure) onSave} callback. */
    boolean isSaveSupported() {
        saveHandler != null
    }

    /**
     * Invokes the registered {@link #onSave(Closure) onSave} callback with the original script
     * {@code source}, the target {@code outputStream} and the save {@code options}, or throws
     * {@link UnsupportedOperationException} when the script registered none.
     */
    void save(String source, OutputStream outputStream, Map<?, ?> options) {
        if (saveHandler == null) {
            throw new UnsupportedOperationException("Save operation is not supported: no onSave callback registered")
        }
        saveHandler.call(source, outputStream, options)
    }

    // --- reference-wrapper detection (the centralised cross-ref policy) --------------------------

    /**
     * The single subtlety the reflective builder must centralise: a "reference" is modelled two ways
     * in this metamodel - as a direct {@code refers} EReference, or as containment of a {@code *Reference}
     * wrapper carrying a single-valued {@code target}. Adding a resolved element straight into a
     * containment feature would <em>relocate</em> it (EMF containment is exclusive), so for containment
     * references we must instead create the wrapper and set its target.
     *
     * <p>This finds that wrapper purely from Ecore: a concrete subtype of {@code featureType} that has a
     * single-valued, non-containment EReference whose type accepts {@code targetType}. When several
     * qualify (e.g. CapabilityReference and its subtype CapabilityDependency), the most general one is
     * chosen. Returns {@code null} when no wrapper exists (a genuine inline containment).</p>
     */
    EClass referenceWrapperFor(EObject base, EClass featureType, EClass targetType) {
        String key = featureType.name + '->' + targetType.name
        EClass cached = wrapperCache[key]
        if (cached != null) {
            return cached.is(NONE) ? null : cached
        }
		
		List<EClass> candidates = resolver.candidates(base, featureType, targetType)

        // Drop any candidate that is a subtype of another candidate -> keep the most general wrapper(s).
        List<EClass> roots = candidates.findAll { EClass c ->
            !candidates.any { EClass o -> !o.is(c) && o.isSuperTypeOf(c) }
        }

        EClass result = roots.size() == 1 ? roots[0] : null
        wrapperCache[key] = (result == null ? NONE : result)
        result
    }

    // --- entry points ---------------------------------------------------------------------------

    /**
     * Install top-level names where {@code groovy.lang.Script.invokeMethod} will find them: every
     * concrete EClass with an <em>unambiguous</em> simple name becomes a root entry point
     * ({@code product { }}, {@code persona { }}, ...), plus {@code ref}, {@code dsl} and the
     * {@code eObject('<name>') { }} entry point. {@code eObject} accepts a simple, qualified
     * ({@code 'c4.Component'}) or URI ({@code '<nsURI>#Component'}) name, so a class can be addressed
     * even when its simple name collides across packages in a resource set - see
     * {@link Resolver#classByName(EObject, String)}.
     */
    void installInto(Map<String, Object> bindings) {
        bindings.ref = this.&ref
        bindings.dsl = this
        bindings.onSave = this.&onSave
        bindings.eObject = { String name, Closure cl ->
            EClass type = resolver.classByName(null, name)
            if (type == null) {
                throw new IllegalArgumentException("Cannot resolve type '${name}'")
            }
            root(type, cl)
        }
        resolver.names().each { String name, EClass type ->
            bindings[name] = { Closure cl -> root(type, cl) }
        }

        bindings.putAll(resolver.bindings())
    }

    EObject root(EClass type, Closure cl) {
        EObject element = create(type)
        mark(element, null)
        run(cl, new ReflectiveBuilder(this, element))
        roots << element
        element
    }

    /** Runs a configuration closure against a builder delegate with DELEGATE_FIRST resolution. */
    static void run(Closure cl, Object delegate) {
        Closure copy = (Closure) cl.clone()
        copy.delegate = delegate
        copy.resolveStrategy = Closure.DELEGATE_FIRST
        copy.call()
    }

    /**
     * The wrapper's {@code target} feature: its single-valued, non-containment EReference whose type
     * accepts {@code targetType}. Named by convention {@code target} across the metamodel, but detected
     * structurally so the convention isn't load-bearing.
     */
    EReference targetFeature(EClass wrapper, EClass targetType) {
        wrapper.EAllReferences.find { EReference r ->
            !r.containment && !r.many && ((EClass) r.EType).isSuperTypeOf(targetType)
        }
    }

}
