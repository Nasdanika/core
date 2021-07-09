package org.nasdanika.emf.persistence;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Adapter interface to track features which were loaded. 
 * May be used when some features can be either loaded or computed.
 * @author Pavel
 *
 */
public interface LoadTracker {
	
	/**
	 * Feature is already loaded.
	 * This method can be used to decide whether to inject 
	 * a feature value derived from other loaded features 
	 * it a feature is not yet loaded. 
	 * @param feature
	 * @return
	 */
	boolean isLoaded(EStructuralFeature feature);
	
	/**
	 * Feature is being loaded. This method
	 * returns true only when the loader calls the feature setter.
	 * It can be used to decide whether to trigger computations of  
	 * features derived from this feature.
	 * @param feature
	 * @return
	 */
	boolean isLoading(EStructuralFeature feature);
	
	/**
	 * Returns "raw" value for non-changeable (derived) features.
	 * This value can be used to derive (compute) feature value.
	 * For changeable features returns null because the value has already been injected into the object.
	 * @param feature
	 * @return
	 */
	Object get(EStructuralFeature feature);

}
