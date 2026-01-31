package org.nasdanika.drawio;

public interface Geometry extends Rectangle {
	
	Rectangle getAlternateBounds();
	
	void setRelative(boolean relative);
	
	boolean isRelative();
	
	/**
	 * Points within the geometry element
	 * @return
	 */
	PointList getPoints();	

}
