package org.nasdanika.drawio;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * A diagram may have one or more layers.
 * @author Pavel
 *
 */
public interface Layer<L extends Layer<L>> extends ModelElement<L> {

	/**
	 * Layer elements
	 * @return
	 */
	default List<LayerElement<?>> getElements() {
		return getChildren();
	}
	
	@Override
	List<LayerElement<?>> getChildren();
	
	/**
	 * Creates a new node
	 * @return
	 */
	Node createNode();
	
	/**
	 * Creates a new connection
	 * @param source Source node, can be null
	 * @param target Target node, can be null
	 * @return
	 */
	Connection createConnection(Connectable source, Connectable target);
	
	/**
	 * Creates layer elements from graph elements.
	 * This method can be used to generate diagrams from simpler 
	 * structures which are easier to construct and are not
	 * Draw.io specific.
	 * @param element
	 */
	void populate(org.nasdanika.graph.Element element, BiConsumer<org.nasdanika.graph.Element, ModelElement<?>> configurator);
	
}
