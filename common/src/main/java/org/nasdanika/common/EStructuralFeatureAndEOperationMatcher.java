package org.nasdanika.common;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Mix-in interface for classes which do {@link EStructuralFeature} and {@link EOperation} matching by feature/operation id, {@link EClass} ID, and {@link EPackage} nsURi.
 * Can be implemented or instantiated and delegated to.
 */
public interface EStructuralFeatureAndEOperationMatcher {
	
	EStructuralFeatureAndEOperationMatcher INSTANCE = new EStructuralFeatureAndEOperationMatcher() {};
	
	static EStructuralFeatureAndEOperationMatcher createFeatureMatcher(java.util.function.Function<String,EPackage> ePackageResolver) {
		return new EStructuralFeatureAndEOperationMatcher() {
			
			@Override
			public EPackage getEPackage(String nsURI, Collection<EPackage> contextEPackages) {
				if (ePackageResolver != null) {
					EPackage ePackage = ePackageResolver.apply(nsURI);
					if (ePackage != null) {
						return ePackage;
					}
				}
				return EStructuralFeatureAndEOperationMatcher.super.getEPackage(nsURI, contextEPackages);
			}
			
		};
	}
		
	/**
	 * Matches feature by its id, class id, and package nsURI
	 * This implementation performs exact match with the declaring class. 
	 * Override to implement matching by ID in subtypes using EClass.getFeatureID(). 
	 * This would require implementing look
	 * @param feature
	 * @param nsURI
	 * @param classID
	 * @param featureID
	 * @return
	 */
	default boolean matchEStructuralFeature(String nsURI, int classID, int featureID, EClass contextEClass, EStructuralFeature feature) {
		EClass eClass = getEClass(nsURI, classID, contextEClass, feature.getEContainingClass());
		return eClass != null && eClass.getFeatureID(feature) == featureID;
	}
	
	default boolean matchEOperation(String nsURI, int classID, int operationID, EClass contextEClass, EOperation operation) {
		EClass eClass = getEClass(nsURI, classID, contextEClass, operation.getEContainingClass());
		return eClass != null && eClass.getOperationID(operation) == operationID;
	}
	
	default EClass getEClass(String nsURI, int classID, EClass... contextEClasses) {		
		Collection<EPackage> contextEPackages = new HashSet<>();
		for (EClass contextEClass: contextEClasses) {
			if (contextEClass != null) {
				contextEPackages.add(contextEClass.getEPackage());
				
				contextEClass
					.getEAllSuperTypes()
					.stream()
					.map(EClass::getEPackage)
					.distinct()
					.forEach(contextEPackages::add);
			}
		}
				
		EPackage ePackage = getEPackage(nsURI, contextEPackages);
		if (ePackage != null) {
			for (EClassifier eClassifier: ePackage.getEClassifiers()) {
				if (eClassifier.getClassifierID() == classID) {
					if (eClassifier instanceof EClass) {
						return (EClass) eClassifier;
					}
					throw new IllegalArgumentException("Not an EClass for ID " + classID + " in EPackage " + nsURI + " - " + eClassifier);				
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns ePackage by NsURI. Used in matching features and operations. 
	 * This implementation returns context {@link EPackage} if nsURI matches. 
	 * Override if matching by non-context packages is required. 
	 * @param nsURI
	 * @return {@link EPackage} by nsURI or null
	 */
	default EPackage getEPackage(String nsURI, Collection<EPackage> contextEPackages) {
		for (EPackage contextEPackage: contextEPackages) {
			if (contextEPackage != null && contextEPackage.getNsURI().equals(nsURI)) {
				return contextEPackage;
			}
		}
		return null;
	}	

}
