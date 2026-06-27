package org.nasdanika.groovy;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * Resolves against a list of {@link EPackage}s. It is used by {@link DslResourceContentsHandler} to resolve.
 */
public abstract class EPackageResolver extends AbstractResolver {
	
	private Map<String, EClass> byName = new java.util.HashMap<>();
	private Map<Class<?>, EClass> byInstanceClass = new java.util.HashMap<>();
	
	public EPackageResolver(EPackage... ePackages) {
		for (EPackage ePkg: ePackages) {
			for (EClassifier eClassifier: ePkg.getEClassifiers()) {
				if (eClassifier instanceof EClass eClass && !eClass.isAbstract() && !eClass.isInterface()) {
					byName.putIfAbsent(org.apache.commons.lang3.StringUtils.uncapitalize(eClass.getName()), eClass);
					if (eClass.getInstanceClass() != null) {
						byInstanceClass.putIfAbsent(eClass.getInstanceClass(), eClass);
					}
				}
			}
		}
	}
	
	@Override
	public EClass classByName(EObject base, String name) {
		return byName.get(name);
	}
	
	@Override
	public EClass classByInstanceClass(EObject base, Class clazz) {
		return byInstanceClass.get(clazz);
	}
	
	@Override
	public List<EClass> candidates(EObject base, EClass featureType, EClass targetType) {
        return byName
    		.values()
    		.stream()
    		.filter(c -> featureType.isSuperTypeOf((EClass) c) && targetFeature((EClass) c, targetType) != null)
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
		
	@Override
	public Map<String, EClass> names() {
		return byName;
	}
	
}