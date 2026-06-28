package org.nasdanika.groovy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Resolves against a tree of {@link EPackage}s (sub-packages included). It is used by
 * {@link DslResourceContentsHandler} to resolve {@link EClass}es named from the DSL.
 *
 * <p>A name passed to {@link #classByName(EObject, String)} may take one of three forms, so a class
 * can always be addressed unambiguously even when many packages share a resource set or several
 * packages declare a class with the same simple name:</p>
 *
 * <ul>
 *   <li><b>simple</b> - a decapitalised class name, {@code 'component'}. Resolved within the package
 *       of {@code base} first (so nested authoring needs no qualification), then globally. A simple
 *       name that matches concrete classes in more than one package with no {@code base} to
 *       disambiguate raises an error listing the qualified alternatives.</li>
 *   <li><b>qualified</b> - {@code 'c4.Component'} or {@code 'architecture.c4.Component'}: the trailing
 *       segment is the class name, the leading segments are an EPackage name path matched against the
 *       package's full path or any trailing sub-path of it.</li>
 *   <li><b>URI</b> - {@code '<nsURI>#<ClassName>'}: the package is looked up by namespace URI (in this
 *       resolver's packages, then the resource set's package registry).</li>
 * </ul>
 */
public abstract class EPackageResolver extends AbstractResolver {

	/** Decapitalised simple name -&gt; concrete EClasses across all packages (may collide). */
	private final Map<String, List<EClass>> bySimpleName = new LinkedHashMap<>();
	private final Map<Class<?>, EClass> byInstanceClass = new java.util.HashMap<>();
	/** Every concrete EClass, used for qualified- and URI-form lookups. */
	private final List<EClass> allClasses = new ArrayList<>();

	public EPackageResolver(EPackage... ePackages) {
		for (EPackage ePkg : ePackages) {
			index(ePkg);
		}
	}

	private void index(EPackage ePkg) {
		for (EClassifier eClassifier : ePkg.getEClassifiers()) {
			if (eClassifier instanceof EClass eClass && !eClass.isAbstract() && !eClass.isInterface()) {
				allClasses.add(eClass);
				bySimpleName
					.computeIfAbsent(StringUtils.uncapitalize(eClass.getName()), k -> new ArrayList<>())
					.add(eClass);
				if (eClass.getInstanceClass() != null) {
					byInstanceClass.putIfAbsent(eClass.getInstanceClass(), eClass);
				}
			}
		}
		for (EPackage subPkg : ePkg.getESubpackages()) {
			index(subPkg);
		}
	}

	@Override
	public EClass classByName(EObject base, String name) {
		if (name == null) {
			return null;
		}
		name = name.trim();

		// URI form: '<nsURI>#<ClassName>'
		int hash = name.lastIndexOf('#');
		if (hash >= 0) {
			EPackage ePkg = getEPackage(name.substring(0, hash));
			return ePkg == null ? null : concreteClass(ePkg, name.substring(hash + 1));
		}

		// Qualified form: '<pkgPath>.<ClassName>' (one or more package segments)
		int dot = name.lastIndexOf('.');
		if (dot >= 0) {
			String pkgPath = name.substring(0, dot);
			String className = name.substring(dot + 1);
			List<EClass> matches = allClasses.stream()
				.filter(c -> nameMatches(c, className) && packageMatches(c.getEPackage(), pkgPath))
				.toList();
			return single(matches, name);
		}

		// Simple form: prefer the package of base, then fall back to the global index.
		if (base != null) {
			EClass scoped = concreteClass(base.eClass().getEPackage(), name);
			if (scoped != null) {
				return scoped;
			}
		}
		return single(bySimpleName.getOrDefault(name, List.of()), name);
	}

	@Override
	public EClass classByInstanceClass(EObject base, Class clazz) {
		return byInstanceClass.get(clazz);
	}

	@Override
	public List<EClass> candidates(EObject base, EClass featureType, EClass targetType) {
		return allClasses
			.stream()
			.filter(c -> featureType.isSuperTypeOf(c) && targetFeature(c, targetType) != null)
			.toList();
	}

	protected EReference targetFeature(EClass wrapper, EClass targetType) {
		return wrapper
			.getEAllReferences()
			.stream()
			.filter(r -> !r.isContainment() && !r.isMany() && r.getEReferenceType().isSuperTypeOf(targetType))
			.findFirst()
			.orElse(null);
	}

	/** Unambiguous simple names installed as top-level entry points; colliding names use {@code eObject('...')}. */
	@Override
	public Map<String, EClass> names() {
		Map<String, EClass> result = new LinkedHashMap<>();
		bySimpleName.forEach((simpleName, classes) -> {
			if (classes.size() == 1) {
				result.put(simpleName, classes.get(0));
			}
		});
		return result;
	}

	// --- helpers --------------------------------------------------------------------------------

	/** Reduce a match list to a single EClass, raising a qualified-alternatives error on ambiguity. */
	private EClass single(List<EClass> matches, String name) {
		if (matches.isEmpty()) {
			return null;
		}
		if (matches.size() == 1) {
			return matches.get(0);
		}
		String alternatives = matches.stream()
			.map(c -> qualifiedName(c) + " (" + c.getEPackage().getNsURI() + "#" + c.getName() + ")")
			.collect(Collectors.joining(", "));
		throw new IllegalArgumentException(
			"Ambiguous name '" + name + "'; qualify it as one of: " + alternatives);
	}

	private boolean nameMatches(EClass eClass, String className) {
		return eClass.getName().equals(className)
			|| StringUtils.uncapitalize(eClass.getName()).equals(className);
	}

	/** A package matches {@code pkgPath} when the path equals, or is a trailing sub-path of, its full path. */
	private boolean packageMatches(EPackage ePkg, String pkgPath) {
		String full = qualifiedPath(ePkg);
		return full.equals(pkgPath) || full.endsWith("." + pkgPath);
	}

	private String qualifiedName(EClass eClass) {
		return qualifiedPath(eClass.getEPackage()) + "." + eClass.getName();
	}

	/** Dotted EPackage name path from the root package down, e.g. {@code architecture.c4}. */
	private String qualifiedPath(EPackage ePkg) {
		StringBuilder sb = new StringBuilder();
		for (EPackage p = ePkg; p != null; p = p.getESuperPackage()) {
			if (sb.length() > 0) {
				sb.insert(0, '.');
			}
			sb.insert(0, p.getName());
		}
		return sb.toString();
	}

	private EClass concreteClass(EPackage ePkg, String className) {
		EClassifier classifier = ePkg.getEClassifier(className);
		if (!(classifier instanceof EClass)) {
			classifier = ePkg.getEClassifier(StringUtils.capitalize(className));
		}
		if (classifier instanceof EClass eClass && !eClass.isAbstract() && !eClass.isInterface()) {
			return eClass;
		}
		return null;
	}

	private EPackage getEPackage(String nsURI) {
		for (EClass c : allClasses) {
			if (nsURI.equals(c.getEPackage().getNsURI())) {
				return c.getEPackage();
			}
		}
		ResourceSet resourceSet = getResourceSet();
		return resourceSet == null ? null : resourceSet.getPackageRegistry().getEPackage(nsURI);
	}

}
