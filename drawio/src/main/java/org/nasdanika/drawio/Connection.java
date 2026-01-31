package org.nasdanika.drawio;

import org.nasdanika.drawio.style.ConnectionStyle;

/**
 * Connection between two nodes. Both source and target can be null.
 * @author Pavel
 *
 */
public interface Connection extends LayerElement<Connection>, org.nasdanika.graph.Connection {
	
	@Override
	Connectable getSource();
	
	void setSource(Connectable source);
	
	Point setSourcePoint(double x, double y);
	
	Point getSourcePoint();
	
	@Override
	Connectable getTarget();
	
	void setTarget(Connectable target);
		
	Point setTargetPoint(double x, double y);
	
	Point getTargetPoint();
	
	PointList getPoints();
	
	Point getOffset();
	
	Point setOffset(double x, double y);
	
	@Override
	ConnectionStyle getStyle();
		
	/**
	 * Creates a new child node. For example, a label
	 * @return
	 */
	Node createNode();
	
}
