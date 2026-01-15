package org.nasdanika.drawio.impl;

import java.util.function.Function;

import org.nasdanika.drawio.Geometry;
import org.nasdanika.drawio.Rectangle;
import org.w3c.dom.Element;

public class GeometryImpl extends RectangleImpl implements Geometry {

	public GeometryImpl(Function<Boolean, Element> elementProvider) {
		super(elementProvider);
	}

	@Override
	public Rectangle getAlternateBounds() {
		throw new UnsupportedOperationException();
	}

}
