package org.nasdanika.drawio.impl;

import java.util.function.Function;

import org.nasdanika.drawio.Point;
import org.w3c.dom.Element;

class PointImpl implements Point {
	
	static final String ATTRIBUTE_X = "x"; 
	static final String ATTRIBUTE_Y = "y"; 	
	
	protected Function<Boolean, Element> elementProvider;

	public PointImpl(Function<Boolean, Element> elementProvider) {
		this.elementProvider = elementProvider;
	}
	
	protected boolean isEmpty(Element element) {
		return false; 
	}

	@Override
	public double getX() {
		Element element = getElement();
		return element != null && element.hasAttribute(ATTRIBUTE_X) ? Double.parseDouble(element.getAttribute(ATTRIBUTE_X)) : 0;
	}

	@Override
	public double getY() {
		Element element = getElement();
		return element != null && element.hasAttribute(ATTRIBUTE_Y) ? Double.parseDouble(element.getAttribute(ATTRIBUTE_Y)) : 0;
	}

	@Override
	public void setX(double x) {
		Element element = elementProvider.apply(true);
		if (x == 0) {
			element.removeAttribute(ATTRIBUTE_X);
			if (isEmpty(element)) {
				element.getParentNode().removeChild(element);
			}
		} else {
			element.setAttribute(ATTRIBUTE_X, String.valueOf(x));
		}
	}

	@Override
	public void setY(double y) {
		Element element = elementProvider.apply(true);
		if (y == 0) {
			element.removeAttribute(ATTRIBUTE_Y);
			if (isEmpty(element)) {
				element.getParentNode().removeChild(element);
			}
		} else {
			element.setAttribute(ATTRIBUTE_Y, String.valueOf(y));
		}
	}
	
	@Override
	public Element getElement() {
		return elementProvider.apply(false);
	}

	@Override
	public void setLocation(double x, double y) {
		Element element = elementProvider.apply(true);
		if (x == 0) {
			element.removeAttribute(ATTRIBUTE_X);
		} else {
			element.setAttribute(ATTRIBUTE_X, String.valueOf(x));
		}
		if (y == 0) {
			element.removeAttribute(ATTRIBUTE_Y);
		} else {
			element.setAttribute(ATTRIBUTE_Y, String.valueOf(y));
		}
		
		if (isEmpty(element)) {
			element.getParentNode().removeChild(element);
		}		
	}
	
	@Override
	public String toString() {
		return super.toString() + "[" + getX() + ", " + getY() + "]";
	}

}
