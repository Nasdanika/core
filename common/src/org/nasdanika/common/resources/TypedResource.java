package org.nasdanika.common.resources;

/**
 * Resource bound to {@link TypedEntity} to serve as a common super-interface for
 * {@link TypedEntity} and {@link TypedEntityContainer}.
 * @author Pavel
 *
 */
public interface TypedResource<T> extends Resource<TypedEntity<T>> {

}
