package org.nasdanika.groovy.dsl

import org.eclipse.emf.common.util.Enumerator
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil

/**
 * A single generic builder that replaces the hand-written one-class-per-EClass / one-method-per-feature
 * builders. It wraps an {@link EObject} and drives the entire authoring surface off
 * {@code element.eClass().getEAllStructuralFeatures()} - so multiple inheritance (e.g. ProductModel
 * mixing in four domains) and any future xcore change are covered with no code change.
 *
 * <p>Two-level dispatch in {@link #methodMissing}:</p>
 * <ol>
 *   <li><b>Feature dispatch</b> - the call name is a structural feature of the current EClass
 *       ({@code name 'x'}, {@code lifecycle 'Available'}, {@code addresses 'kyc-latency'},
 *       {@code concerns(Concern) { }}).</li>
 *   <li><b>Type dispatch</b> - the call name is a concrete EClass; a child is created and auto-routed
 *       to the unique containment feature that accepts it ({@code goal { }} where unambiguous).</li>
 * </ol>
 *
 * <p>All cross-references - direct {@code refers} and {@code *Reference} wrappers alike - funnel through
 * the one {@link #resolveReference} / {@link #attachReference} pair, so there is no per-feature
 * reference code to drift. See groovy-kotlin-dsl-design.md &sect;5.6.</p>
 */
class ReflectiveBuilder {

    protected final DslContext ctx
    protected final EObject element
    protected final EClass eClass

    ReflectiveBuilder(DslContext ctx, EObject element) {
        this.ctx = ctx
        this.element = element
        this.eClass = element.eClass()
    }

    EObject getElement() {
        element
    }

    // --- global registration --------------------------------------------------------------------

    /**
     * Registers the current element as a global object under the given URI via
     * {@link DslContext#global(Object, EObject)}. Defined as a real method so it always wins over
     * feature/type dispatch: {@code global 'uri'} registers even on an EClass that declares a
     * {@code global} feature. That feature stays settable through the assignment form
     * {@code global = value} (which routes through {@link #propertyMissing}, not this method) and
     * readable through the read form - a real method never intercepts property access.
     */
    EObject global(String uri) {
        ctx.global(uri, element)
        element
    }
	
	/**
	 * 
	 * @param uri
	 * @return
	 */
	
	EObject proxy(String uri) {
		((InternalEObject) element).eSetProxyURI(URI.createURI(uri))
		element
	}

    // --- dispatch -------------------------------------------------------------------------------

    def methodMissing(String name, Object args) {
        Object[] a = args as Object[]

        EStructuralFeature f = eClass.getEStructuralFeature(name)
        if (f != null) {
            return invokeFeature(f, a)
        }

        EClass childType = ctx.classByName(element, name)
        if (childType != null) {
            return createChildAutoRouted(childType, a)
        }

        throw new MissingMethodException(name, ReflectiveBuilder, a)
    }

    /** Assignment form: {@code name = 'x'} as an alternative to {@code name 'x'}. */
    def propertyMissing(String name, Object value) {
        EStructuralFeature f = eClass.getEStructuralFeature(name)
        if (f == null) {
            throw new MissingPropertyException(name, ReflectiveBuilder)
        }
        invokeFeature(f, [value] as Object[])
    }

    /** Read form: lets a closure read back a feature value. */
    def propertyMissing(String name) {
        EStructuralFeature f = eClass.getEStructuralFeature(name)
        if (f == null) {
            throw new MissingPropertyException(name, ReflectiveBuilder)
        }
        element.eGet(f)
    }

    // --- feature dispatch -----------------------------------------------------------------------

    private Object invokeFeature(EStructuralFeature f, Object[] a) {
        if (!f.changeable || f.derived) {
            throw new IllegalStateException("Feature '${eClass.name}.${f.name}' is derived/read-only and cannot be set")
        }
        if (f instanceof EAttribute) {
			a.each { setAttribute((EAttribute) f, it) }
            return null
        }
        EReference r = (EReference) f
        if (r.containment) {
            if (a.any { it instanceof Closure }) {
                return containChild(r, a)          // inline build: addresses { goal { } }
            }
            a.each { resolveReference(r, it) }     // reference into a containment feature -> wrapped
            return null
        }
        a.each { resolveReference(r, it) }         // direct refers reference
        null
    }

    // --- attributes -----------------------------------------------------------------------------

    private void setAttribute(EAttribute f, Object value) {
        Object coerced = coerce(f, value)
        if (f.many) {
            (element.eGet(f) as EList).add(coerced)
        } else {
            element.eSet(f, coerced)
        }
    }

    private Object coerce(EAttribute f, Object value) {
        if (value == null) {
            return null
        }
        EDataType dt = f.EAttributeType
        if (dt instanceof EEnum) {
            if (value instanceof Enumerator) {
                return value
            }
            String s = value.toString()
            EEnum e = (EEnum) dt
            EEnumLiteral lit = e.getEEnumLiteralByLiteral(s) ?: e.getEEnumLiteral(s)
            if (lit == null) {
                throw new IllegalArgumentException("Unknown ${e.name} literal: '${s}'")
            }
            return lit.instance
        }
        Class<?> ic = dt.instanceClass
        if (ic != null && ic.isInstance(value)) {
            return value                          // already the right Java type (incl. wraps types)
        }
        if (value instanceof CharSequence) {
            try {
                return EcoreUtil.createFromString(dt, value.toString())
            } catch (Exception ignore) {
                // fall through; let eSet do its own coercion or fail with EMF's message
            }
        }
        value
    }

    // --- containment (inline build) -------------------------------------------------------------

    private EObject containChild(EReference r, Object[] a) {
        Closure cl = (Closure) a.find { it instanceof Closure }
        EClass type = resolveConcreteType(r, a)
        EObject child = ctx.create(type)
        if (cl != null) {
            DslContext.run(cl, new ReflectiveBuilder(ctx, child))
        }
        addOrSet(r, child)
        child
    }

    /** Concrete type for a containment: an explicit token if given, else the feature type if concrete. */
    private EClass resolveConcreteType(EReference r, Object[] a) {
        Object token = a.find { !(it instanceof Closure) }
        if (token != null) {
            EClass type = ctx.toEClass(token)
            if (type == null) {
                throw new IllegalArgumentException("Unknown type '${token}' for ${eClass.name}.${r.name}")
            }
            if (!((EClass) r.EType).isSuperTypeOf(type)) {
                throw new IllegalArgumentException("${type.name} is not a ${r.EType.name} for ${eClass.name}.${r.name}")
            }
            return type
        }
        EClass featureType = (EClass) r.EType
        if (featureType.abstract || featureType.interface) {
            throw new IllegalStateException(
                "${eClass.name}.${r.name} is typed by abstract ${featureType.name}; " +
                "name a concrete type, e.g. ${r.name}('<Type>') { ... }")
        }
        featureType
    }

    /** Type-dispatch: route a {@code goal { }}-style call to the unique containment feature accepting it. */
    private EObject createChildAutoRouted(EClass type, Object[] a) {
        List<EReference> candidates = eClass.EAllContainments.findAll { EReference r ->
            r.changeable && !r.derived && ((EClass) r.EType).isSuperTypeOf(type)
        }
        if (candidates.isEmpty()) {
            throw new MissingMethodException(type.name, ReflectiveBuilder, a)
        }
        if (candidates.size() > 1) {
            throw new IllegalStateException(
                "Ambiguous: ${type.name} fits containment features ${candidates*.name} on ${eClass.name}; " +
                "use the feature form, e.g. ${candidates[0].name}('${type.name}') { ... }")
        }
        EReference r = candidates[0]
        Closure cl = (Closure) a.find { it instanceof Closure }
        EObject child = ctx.create(type)
        if (cl != null) {
            DslContext.run(cl, new ReflectiveBuilder(ctx, child))
        }
        addOrSet(r, child)
        child
    }

    // --- the unified cross-reference resolver ---------------------------------------------------

    private void resolveReference(EReference r, Object arg) {
        if (arg instanceof EObject) {
            attachReference(r, (EObject) arg)                 // tier 1 (by var)
            return
        }
        if (arg instanceof CharSequence) {
            String key = arg.toString()
            ctx.defer {                                        // relative path / cross-resource URI
                EObject target = ctx.resolveRelative(element, key)
                if (target == null) {
                    throw new IllegalStateException("Unresolved reference '${key}' for ${eClass.name}.${r.name}")
                }
                if (!r.EType.isInstance(target)) {
                    throw new IllegalStateException(
                        "'${key}' resolves to ${target.eClass().name}, not a ${r.EType.name} for ${r.name}")
                }
                attachReference(r, target)
            }
            return
        }
        throw new IllegalArgumentException("Cannot use ${arg?.getClass()?.name} as a reference for ${r.name}")
    }

    /**
     * Attach a resolved target. Direct {@code refers} references are set straight; containment
     * references are wrapped in their {@code *Reference} type (so an existing element isn't relocated).
     * If no wrapper exists and the target is already contained, refuse rather than silently steal it.
     */
    private void attachReference(EReference r, EObject target) {
        if (!r.containment) {
            addOrSet(r, target)
            return
        }
        EClass wrapperType = ctx.referenceWrapperFor(element, (EClass) r.EType, target.eClass())
        if (wrapperType != null) {
            EObject wrapper = ctx.create(wrapperType)
            EReference targetFeature = ctx.targetFeature(wrapperType, target.eClass())
            wrapper.eSet(targetFeature, target)
            addOrSet(r, wrapper)
            return
        }
        if (target.eContainer() != null) {
            throw new IllegalStateException(
                "Attaching already-contained ${target.eClass().name} to containment " +
                "${eClass.name}.${r.name} would relocate it, and no reference wrapper was found")
        }
        addOrSet(r, target)
    }

    private void addOrSet(EReference r, EObject value) {
        if (r.many) {
            (element.eGet(r) as EList).add(value)
        } else {
            element.eSet(r, value)
        }
    }

}
