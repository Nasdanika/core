package org.nasdanika.common;

import java.util.function.BinaryOperator;

/**
 * Something that can be composed to create an aggregate. E.g. {@link AccessController}s can be composed with AND operation,
 * {@link Context}s can be composed by chaining.
 * @author Pavel
 *
 * @param <T>
 */
public interface Composeable<T> {
	
	/**
	 * @param <T> Instances of T shall implement {@link Composeable}.
	 * @return Composing operator which can be use in reducing streams of {@link Composeable}s to a single composeable.
	 */
	@SuppressWarnings("unchecked")
	static <T> BinaryOperator<T> composer() {
		return (a,b) -> a == null ? b : ((Composeable<T>) a).compose(b);
	}

	/**
	 * Returns a composition of this instance and the other with this instance being 
	 * the first if the order of composition is important.
	 * @param other
	 * @return
	 */
	T compose(T other);
	
}
