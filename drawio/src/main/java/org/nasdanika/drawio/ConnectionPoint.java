package org.nasdanika.drawio;

public interface ConnectionPoint extends Point {
		
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

}
