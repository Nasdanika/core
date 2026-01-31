package org.nasdanika.drawio.impl;

import java.util.function.Function;

import org.nasdanika.drawio.Geometry;
import org.nasdanika.drawio.PointList;
import org.nasdanika.drawio.Rectangle;
import org.w3c.dom.Element;

public class GeometryImpl extends RectangleImpl implements Geometry {

	static final String ATTRIBUTE_RELATIVE = "relative"; 
		
	public GeometryImpl(Function<Boolean, Element> elementProvider) {
		super(elementProvider);
	}
	
	@Override
	public PointList getPoints() {
		return new GeometryPointListImpl(this);
	}

	@Override
	public Rectangle getAlternateBounds() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRelative() {
		Element element = getElement();
		return element != null && element.hasAttribute(ATTRIBUTE_RELATIVE) && "1".equals(element.getAttribute(ATTRIBUTE_RELATIVE));
	}

	@Override
	public void setRelative(boolean relative) {
		Element element = elementProvider.apply(true);
		if (relative) {
			element.setAttribute(ATTRIBUTE_RELATIVE, "1");
		} else {
			element.removeAttribute(ATTRIBUTE_RELATIVE);
		}
	}

}
