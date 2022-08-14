package org.nasdanika.drawio;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;

/**
 * {@link Document} element, including the Document itself, backed by org.w3c.dom.Element.
 * @author Pavel
 *
 */
public interface Element extends org.nasdanika.graph.Element {
	
	/**
	 * @return The underlying XML element.
	 */
	org.w3c.dom.Element getElement();
	
	/**
	 * Accepts the visitor in children first way.
	 * @param visitor
	 */
	default void accept(Consumer<? super Element> visitor, ConnectionBase connectionBase) {
		accept((e, cr) -> { visitor.accept(e); return null; }, connectionBase);
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<Element, Map<Element, T>, T> visitor, ConnectionBase connectionBase);
		
	@Override
	default <T> T accept(BiFunction<? super org.nasdanika.graph.Element, Map<? extends org.nasdanika.graph.Element, T>, T> visitor) {
		BiFunction<Element, Map<Element, T>, T> visitorAdapter = (element, childMappings) -> {
			return visitor.apply(element, childMappings);
		};
		return accept(visitorAdapter, ConnectionBase.PARENT);
	}
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element> stream(ConnectionBase connectionBase) {
		BiFunction<Element, Map<Element, Stream<Element>>, Stream<Element>> visitor = (element, childMappings) -> {
			return childMappings.values().stream().reduce(Stream.of(element), (a,b) -> Stream.concat(a, b));
		};
		return accept(visitor, connectionBase);
	}; 
		
	URI getURI();	

}
