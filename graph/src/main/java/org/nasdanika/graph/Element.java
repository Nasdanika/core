package org.nasdanika.graph;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Graph element accepts visitors and supports streaming. 
 * @author Pavel
 *
 */
public interface Element {
	
	/**
	 * Accepts the visitor in children first way.
	 * @param visitor
	 */
	default void accept(Consumer<? super Element> visitor) {
		accept((e, cr) -> { visitor.accept(e); return null; });
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor);
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element> stream() {
		BiFunction<Element, Map<? extends Element, Stream<Element>>, Stream<Element>> visitor = (element, childMappings) -> {
			return childMappings.values().stream().reduce(Stream.of(element), (a,b) -> Stream.concat(a, b));
		};
		return accept(visitor);
	}; 

}
