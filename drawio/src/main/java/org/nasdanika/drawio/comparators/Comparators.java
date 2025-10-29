package org.nasdanika.drawio.comparators;

public enum Comparators {

	label("label"),
	labelDescending("label-descending"),
	
	property("property"),
	propertyDescending("property-descending"),
	
	clockwise("clockwise"),
	counterclockwise("counterclockwise"),
	
	flow("flow"),
	reverseFlow("reverse-flow"),

	rightDown("right-down"),
	rightUp("right-up"),
	
	leftDown("left-down"),
	leftUp("left-up"),
	
	downRight("down-right"),
	downLeft("down-left"),
	
	upRight("up-right"),
	upLeft("up-left"),
			
	position("position"),
	positionReversed("reverse-position");		
	
	Comparators(String key) {
		this.key = key;
	}
	
	public final String key;
	
}