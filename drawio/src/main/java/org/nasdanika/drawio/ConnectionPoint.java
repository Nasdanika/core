package org.nasdanika.drawio;

import org.w3c.dom.Element;

public interface ConnectionPoint extends Connectable, Point {

	/**
	 * Connection points do not have backing elements
	 */
	@Override
	default Element getElement() {
		return null;
	}
		
	double getDx();
	
	double getDy();
	
	void setDx(double dx);
	
	void setDy(double dy);
	
	default void setLocation(double x, double y, double dx, double dy) {
		setLocation(x, y);
		setDx(dx);
		setDy(dy);
	}
	
	@Override
	default void setLocation(double x, double y) {
		setX(x);
		setY(y);
	}	
	
	Node getNode();
	
	/**
	 * This method can be used on connection points obtained from Connection.getExitPoint() and Connection.getEntryPoint().
	 * These methods always return a connection point even if there is not connection point defined in the connection style.
	 * The method returns true if connection point attributes are defined in the style, false otherwise.
	 * @return
	 */
	boolean isSet();
	
	/**
	 * Removes style attributes associated with the connection point from all its incoming and outgoing connections.
	 */
	void unset();
	
	boolean isPerimeter();
	
	void setPerimeter(boolean perimeter);

}
