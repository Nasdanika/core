package org.nasdanika.drawio;

public interface Rectangle extends Point {
	
	int getWidth();
	
	int getHeight();
	
	void setWidth(int width);
	
	void setHeight(int height);	
	
	void setBounds(int x, int y, int width, int height);

}
