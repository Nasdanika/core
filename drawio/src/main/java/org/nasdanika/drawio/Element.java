package org.nasdanika.drawio;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link Document} element, including the Document itself, backed by org.w3c.dom.Element.
 * @author Pavel
 *
 */
public interface Element {
	
	/**
	 * @return The underlying XML element.
	 */
	org.w3c.dom.Element getElement();
	
	/**
	 * Accepts the visitor in children first way.
	 * @param visitor
	 */
	default void accept(Consumer<Element> visitor, ConnectionBase connectionBase) {
		accept((e, cr) -> { visitor.accept(e); return null; }, connectionBase);
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<Element, Map<Element, T>, T> visitor, ConnectionBase connectionBase);
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element> stream(ConnectionBase connectionBase) {
		return accept((BiFunction<Element, Map<Element, Stream<Element>>, Stream<Element>>) (e, cm) -> cm.values().stream().reduce(Stream.of(e), (a,b) -> Stream.concat(a, b)), connectionBase);
	}; 

}
