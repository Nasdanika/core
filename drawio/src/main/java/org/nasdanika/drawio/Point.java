package org.nasdanika.drawio;

public interface Point {
	
	org.w3c.dom.Element getElement();	
	
	int getX();
	
	int getY();
	
	void setX(int x);
	
	void setY(int y);
	
	void setLocation(int x, int y);

}
