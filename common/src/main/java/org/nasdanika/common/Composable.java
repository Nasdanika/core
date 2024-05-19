package org.nasdanika.common;

/**
 * Something that can be composed to create an aggregate. E.g. {@link AccessController}s can be composed with AND operation,
 * {@link Context}s can be composed by chaining.
 * @author Pavel
 *
 * @param <T>
 */
public interface Composable<T> {
	
	/**
	 * @param <T> Instances of T shall implement {@link Composable}.
	 * @return Composing operator which can be use in reducing streams of {@link Composable}s to a single composeable.
	 */
	@SuppressWarnings("unchecked")
	static <T> T composer(T a, T b) {	
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		return ((Composable<T>) a).compose(b);
	}

	/**
	 * Returns a composition of this instance and the other with this instance being 
	 * the first if the order of composition is important.
	 * @param other
	 * @return
	 */
	T compose(T other);
	
}
