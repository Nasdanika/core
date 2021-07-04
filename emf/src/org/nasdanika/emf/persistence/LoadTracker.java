package org.nasdanika.emf.persistence;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Adapter interface to track features which were loaded. 
 * May be used when some features can be either loaded or computed.
 * @author Pavel
 *
 */
public interface LoadTracker {
	
	boolean isLoaded(EStructuralFeature feature);

}
