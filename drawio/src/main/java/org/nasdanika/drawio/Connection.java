package org.nasdanika.drawio;

/**
 * Connection between two nodes. Both source and target can be null.
 * @author Pavel
 *
 */
public interface Connection extends LayerElement, org.nasdanika.graph.Connection {
	
	@Override
	Node getSource();
	
	@Override
	Node getTarget();

}
