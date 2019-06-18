package org.nasdanika.common;

/**
 * Something that can be composed to create an aggregate. E.g. {@link AccessController}s can be composed with AND operation,
 * {@link Context}s can be composed by chaining.
 * @author Pavel
 *
 * @param <T>
 */
public interface Composeable<T> {

	/**
	 * Returns a composition of this instance and the other with this instance being 
	 * the first if the order of composition is important.
	 * @param other
	 * @return
	 */
	T compose(T other);
	
}
