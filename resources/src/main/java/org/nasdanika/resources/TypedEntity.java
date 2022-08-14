package org.nasdanika.resources;

/**
 * Binding of entity state to T, and of E to TypedeEntity&lt;T&gt;.
 * This interface is intended to abstract clients from different implementations of typed entities and containers. 
 * @author Pavel
 *
 * @param <E>
 */
public interface TypedEntity<T> extends Entity<T,TypedEntity<T>>, TypedResource<T> {
	
	/**
	 * Narrowing the return type.
	 */
	@Override
	TypedEntityContainer<T> getParent();

}
