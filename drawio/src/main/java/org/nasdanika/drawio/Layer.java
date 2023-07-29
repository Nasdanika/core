package org.nasdanika.drawio;

import java.util.List;

/**
 * A diagram may have one or more layers.
 * @author Pavel
 *
 */
public interface Layer extends ModelElement {

	/**
	 * Layer elements
	 * @return
	 */
	default List<LayerElement> getElements() {
		return getChildren();
	}
	
	@Override
	List<LayerElement> getChildren();
	
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
	Connection createConnection(Node source, Node target);
	
}
