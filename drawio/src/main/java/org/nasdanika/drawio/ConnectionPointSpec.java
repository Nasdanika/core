package org.nasdanika.drawio;

import org.w3c.dom.Element;

/**
 * Connection point specification (data)
 */
public interface ConnectionPointSpec extends Point {

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
	
	boolean isPerimeter();
	
	void setPerimeter(boolean perimeter);
	
	default void setSpec(ConnectionPointSpec spec) {
		setX(spec.getX());
		setDx(spec.getDx());
		setY(spec.getY());
		setDy(spec.getDy());
		setPerimeter(spec.isPerimeter());
	}
		
	default public boolean specEquals(ConnectionPointSpec spec) {
		return Double.doubleToLongBits(getX()) == Double.doubleToLongBits(spec.getX())
				&& Double.doubleToLongBits(getY()) == Double.doubleToLongBits(spec.getY())
				&& Double.doubleToLongBits(getDx()) == Double.doubleToLongBits(spec.getDx())
				&& Double.doubleToLongBits(getDy()) == Double.doubleToLongBits(spec.getDy())
				&& isPerimeter() == spec.isPerimeter();
	}		

}
