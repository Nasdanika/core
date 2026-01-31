package org.nasdanika.drawio;

public interface Point {
	
	org.w3c.dom.Element getElement();	
	
	double getX();
	
	double getY();
	
	void setX(double x);
	
	void setY(double y);
	
	void setLocation(double x, double y);
	
	String getRole();
	
	void setRole(String role);	

}
