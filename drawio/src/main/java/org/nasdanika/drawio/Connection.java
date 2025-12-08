package org.nasdanika.drawio;

/**
 * Connection between two nodes. Both source and target can be null.
 * @author Pavel
 *
 */
public interface Connection extends LayerElement<Connection>, org.nasdanika.graph.Connection {
	
	@Override
	Node getSource();
	
	void setSource(Node node);
	
	Point setSourcePoint(double x, double y);
	
	Point getSourcePoint();
	
	@Override
	Node getTarget();
	
	void setTarget(Node node);
		
	Point setTargetPoint(double x, double y);
	
	Point getTargetPoint();
	
	PointList getPoints();
	
}
