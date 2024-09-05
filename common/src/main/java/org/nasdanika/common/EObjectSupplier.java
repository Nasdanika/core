package org.nasdanika.common;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 * Service/functional interface providing an {@link EObject}
 * @param <T>
 */
public interface EObjectSupplier<T> {

	Collection<T> getEObjects(ProgressMonitor progressMonitor);
	
}
