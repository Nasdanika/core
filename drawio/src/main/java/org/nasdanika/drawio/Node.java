package org.nasdanika.drawio;

import java.util.List;

import org.nasdanika.drawio.style.NodeStyle;

public interface Node extends Layer<Node>, LayerElement<Node>, Connectable {
		
	Geometry getGeometry();
	
	List<ConnectionPoint> getConnectionPoints();
	
	/**
	 * Factory method.
	 * Creates a connection point for this node. 
	 * @return
	 */
	ConnectionPoint createConnectionPoint();
		
	default ConnectionPoint createConnectionPoint(double x, double y, double dx, double dy) {
		ConnectionPoint cp = createConnectionPoint(x,y);
		cp.setDx(dx);
		cp.setDy(dy);
		return cp;
	}
	
	default ConnectionPoint createConnectionPoint(double x, double y) {
		ConnectionPoint cp = createConnectionPoint();
		cp.setX(x);
		cp.setY(y);
		return cp;
	}		

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
	
	@Override
	NodeStyle getStyle();

}
