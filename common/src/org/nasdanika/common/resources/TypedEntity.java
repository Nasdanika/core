package org.nasdanika.common.resources;

/**
 * Binding of entity state to T, and of E to TypedeEntity&lt;T&gt;.
 * This interface is intended to abstract clients from different implementations of typed entities and containers. 
 * @author Pavel
 *
 * @param <E>
 */
public interface TypedEntity<T> extends Entity<T,TypedEntity<T>> {

}
