package org.nasdanika.common;

import org.eclipse.emf.ecore.EObject;

/**
 * Service/functional interface providing an {@link EObject}
 * @param <T>
 */
public interface EObjectSupplier<T> {

	T getEObject(ProgressMonitor progressMonitor);
	
}
