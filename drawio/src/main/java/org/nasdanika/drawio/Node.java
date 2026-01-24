package org.nasdanika.drawio;

import java.util.List;

public interface Node extends Layer<Node>, LayerElement<Node>, Connectable {
		
	Geometry getGeometry();
	
	List<ConnectionPoint> getConnectionPoints();
	
	/**
	 * Factory method.
	 * Creates a connection point for this node, but doesn't add it to the list of connection points. 
	 * @return
	 */
	ConnectionPoint createConnectionPoint();

	/**
	 * All incoming connections including incoming connections of connection points.
	 * @return
	 */
	List<Connection> getAllIncomingConnections();

	/**
	 * All outgoing connections including outgoing connections of connection points.
	 * @return
	 */
	List<Connection> getAllOutgoingConnections();

}
