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

    final EPackage ePackage
    final Resource resource
    final ResourceSet resourceSet
    final URI baseURI

    /** Decapitalised concrete-class name -> EClass, e.g. {@code 'capability' -> Capability}. */
    private final Map<String, EClass> byName = [:]
    /** id -> element, for tier-2 (by-id) reference resolution. */
    private final Map<String, EObject> byId = [:]
    /** Reference resolvers run after the script has been evaluated (so every id is indexed). */
    private final List<Closure> deferred = []
    /** Top-level elements created by root entry points; the terminal stage adds them to the resource. */
    private final List<EObject> roots = []
    /** Cache for {@link #referenceWrapperFor}: 'featureType->targetType' -> wrapper EClass (or NONE). */
    private final Map<String, EClass> wrapperCache = [:]
    private static final EClass NONE = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass()

    DslContext(EPackage ePackage, Resource resource) {
        this.ePackage = ePackage
        this.resource = resource
        this.resourceSet = resource?.resourceSet
        this.baseURI = resource?.URI
        ePackage.EClassifiers.each { EClassifier c ->
            if (c instanceof EClass && !c.abstract && !c.interface) {
                byName[decapitalize(c.name)] = c
            }
        }
    }

    // --- factory / registry ---------------------------------------------------------------------

    EObject create(EClass type) {
        (EObject) type.EPackage.EFactoryInstance.create(type)
    }

    /** Concrete EClass registered under a decapitalised name, or {@code null}. */
    EClass classByName(String name) {
        byName[name]
    }

    /** Coerce a type token (EClass, Class, or name String) to an EClass, or {@code null}. */
    EClass toEClass(Object token) {
        if (token instanceof EClass) {
            return (EClass) token
        }
        if (token instanceof Class) {
            return (EClass) ePackage.EClassifiers.find { it instanceof EClass && it.instanceClass == token }
        }
        if (token instanceof CharSequence) {
            String s = token.toString()
            return byName[s] ?: byName[decapitalize(s)] ?: (EClass) ePackage.getEClassifier(s)
        }
        null
    }

    // --- id index + reference resolution --------------------------------------------------------

    /** Index an element by its {@code id} attribute, if present. */
    void index(EObject element) {
        EStructuralFeature f = element.eClass().getEStructuralFeature('id')
        if (f != null) {
            Object v = element.eGet(f)
            if (v) {
                byId[v.toString()] = element
            }
        }
    }

    EObject byId(String id) {
        byId[id]
    }

    /**
     * Unified tier-2 (local id) + tier-3 (cross-file URI) lookup. Tries the local id index first,
     * then resolves {@code key} as a (possibly relative) URI through the resource set.
     */
    EObject resolve(String key) {
        EObject local = byId[key]
        if (local != null) {
            return local
        }
        if (resourceSet == null) {
            return null
        }
        try {
            URI uri = URI.createURI(key)
            if (baseURI != null) {
                uri = uri.resolve(baseURI)
            }
            return resourceSet.getEObject(uri, true)
        } catch (Exception ignore) {
            return null
        }
    }

    /**
     * Resolve a reference expression relative to {@code base}.
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
    EObject resolveRelative(EObject base, String expr) {
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
            cur = base != null ? EcoreUtil.getRootContainer(base)
                : (resource != null && !resource.contents.empty ? resource.contents[0] : null)
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

    /** Explicit reference helper: {@code ref('../personas[id=\'pavel\']')}, {@code ref('a.pm#//@x')}. */
    EObject ref(String fragment) {
        EObject resolved = resolveRelative(null, fragment)
        if (resolved == null) {
            throw new IllegalStateException("Cannot resolve reference '" + fragment + "'")
        }
        resolved
    }

    void defer(Closure resolver) {
        deferred << resolver
    }

    /** Called by the terminal normalise stage, after the script has been evaluated. */
    void resolveDeferred() {
        deferred*.call()
    }

    List<EObject> getRoots() {
        roots
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
    EClass referenceWrapperFor(EClass featureType, EClass targetType) {
        String key = featureType.name + '->' + targetType.name
        EClass cached = wrapperCache[key]
        if (cached != null) {
            return cached.is(NONE) ? null : cached
        }

        List<EClass> candidates = ePackage.EClassifiers.findAll { EClassifier c ->
            c instanceof EClass && !c.abstract && !c.interface &&
                featureType.isSuperTypeOf((EClass) c) && targetFeature((EClass) c, targetType) != null
        } as List<EClass>

        // Drop any candidate that is a subtype of another candidate -> keep the most general wrapper(s).
        List<EClass> roots = candidates.findAll { EClass c ->
            !candidates.any { EClass o -> !o.is(c) && o.isSuperTypeOf(c) }
        }

        EClass result = roots.size() == 1 ? roots[0] : null
        wrapperCache[key] = (result == null ? NONE : result)
        result
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

    // --- entry points ---------------------------------------------------------------------------

    /**
     * Install top-level names where {@code groovy.lang.Script.invokeMethod} will find them: every
     * concrete EClass becomes a root entry point ({@code product { }}, {@code persona { }}, ...), plus
     * {@code ref}, {@code factory}, {@code dsl}. Mirrors ProductManagementGroovyDsl.installInto.
     */
    void installInto(Map<String, Object> bindings) {
        bindings.ref = this.&ref
        bindings.dsl = this
        bindings.factory = ePackage.EFactoryInstance
        byName.each { String name, EClass type ->
            bindings[name] = { Closure cl -> root(type, cl) }
        }
    }

    EObject root(EClass type, Closure cl) {
        EObject element = create(type)
        run(cl, new ReflectiveBuilder(this, element))
        index(element)
        roots << element
        element
    }

    /**
     * JavaBeans-style decapitalization, inlined to avoid a {@code java.desktop} ({@code java.beans})
     * module dependency in headless/CLI builds. Mirrors {@code java.beans.Introspector.decapitalize},
     * including the rule that a name whose first two characters are upper case is left unchanged
     * (so acronym-style class names like {@code URI}/{@code URL} are not mangled).
     */
    static String decapitalize(String name) {
        if (!name) {
            return name
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name
        }
        char[] chars = name.toCharArray()
        chars[0] = Character.toLowerCase(chars[0])
        new String(chars)
    }

    /** Runs a configuration closure against a builder delegate with DELEGATE_FIRST resolution. */
    static void run(Closure cl, Object delegate) {
        Closure copy = (Closure) cl.clone()
        copy.delegate = delegate
        copy.resolveStrategy = Closure.DELEGATE_FIRST
        copy.call()
    }

}
