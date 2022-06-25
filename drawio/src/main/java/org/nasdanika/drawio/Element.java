package org.nasdanika.drawio;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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
	default void accept(Consumer<Element> visitor) {
		accept((e, cr) -> { visitor.accept(e); return null; });
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<Element, Map<Element, T>, T> visitor);
	
	/**
	 * @param step
	 * @return A visitor which sizes and arranges nodes in such a way that they do not overlap.
	 */
	static BiFunction<Element, Map<Element, Rectangle>, Rectangle> createNonOverlappingLayoutVisitor(int step) {
		throw new UnsupportedOperationException("TODO - move logic from diagram.gen");
	}	

}
