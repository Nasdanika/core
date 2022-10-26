package org.nasdanika.drawio;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Represents mxGraphModel element
 * @author Pavel
 *
 */
public interface Model extends Element {
	
	/**
	 * @return Diagram root which contains layers.
	 */
	Root getRoot();
		
	/**
	 * @param step
	 * @return A visitor which sizes and arranges nodes in such a way that they do not overlap.
	 */
	static BiFunction<Element, Map<Element, Rectangle>, Rectangle> createNonOverlappingLayoutVisitor(int step) {
		throw new UnsupportedOperationException("TODO - move logic from diagram.gen");
	}	
	
	/**
	 * Containing page
	 * @return
	 */
	Page getPage();

}
