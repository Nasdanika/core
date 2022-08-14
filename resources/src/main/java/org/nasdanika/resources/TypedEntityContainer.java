package org.nasdanika.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * Binds EntityContainer to T and {@link TypedEntity}&lt;T&gt;.
 * This interface is intended to abstract clients from different implementations of typed entities and containers.
 * @author Pavel
 *
 * @param <T> State type.
 */
public interface TypedEntityContainer<T> extends EntityContainer<T,TypedEntity<T>>, TypedResource<T> {
		
	/**
	 * Narrows return type.
	 */
	@Override
	TypedEntityContainer<T> getContainer(String path, ProgressMonitor monitor);
	
	/**
	 * Narrows return type.
	 */
	@Override
	TypedEntityContainer<T> getParent();
	
	/**
	 * Narrows return type.
	 */
	@Override
	TypedResource<T> find(String path, ProgressMonitor monitor);

}
