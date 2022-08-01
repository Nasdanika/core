package org.nasdanika.drawio;

public interface Rectangle extends Point {
	
	double getWidth();
	
	double getHeight();
	
	void setWidth(double width);
	
	void setHeight(double height);	
	
	void setBounds(double x, double y, double width, double height);

}
