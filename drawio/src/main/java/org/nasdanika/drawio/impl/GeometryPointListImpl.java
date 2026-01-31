package org.nasdanika.drawio.impl;

import java.util.AbstractList;
import java.util.List;

import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.PointList;
import org.w3c.dom.Element;

/**
 * Points list which supports remove operation, but not add or set. 
 * Although it supports add(int index, double x, double y) and add(double x, double y) operations.
 */
class GeometryPointListImpl extends AbstractList<Point> implements PointList {
	
	private static final String MX_POINT_ELEMENT = "mxPoint";
	
	private GeometryImpl geometry;

	GeometryPointListImpl(GeometryImpl geometry) {
		this.geometry = geometry;
	}

	@Override
	public int size() {
		return DocumentImpl.getChildrenElements(geometry.getElement(), MX_POINT_ELEMENT).size();		
	}

	@Override
	public Point get(int index) {
		List<Element> points = DocumentImpl.getChildrenElements(geometry.getElement(), MX_POINT_ELEMENT);
		if (index <= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		return new PointImpl(create -> points.get(index));
	}	
	
	@Override
	public Point remove(int index) {
		List<Element> points = DocumentImpl.getChildrenElements(geometry.getElement(), MX_POINT_ELEMENT);
		if (index >= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		
		Element pointElement = points.get(index);
		geometry.getElement().removeChild(pointElement);
		
		return new PointImpl(create -> pointElement);
	}	
	
	@Override
	public Point set(int index, Point element) {
		List<Element> points = DocumentImpl.getChildrenElements(geometry.getElement(), MX_POINT_ELEMENT);
		if (index >= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		Element pointElement = ((PointImpl) element).elementProvider.apply(false);
		Element refPointElement = points.get(index);
		if (refPointElement == pointElement) {
			return element;
		}
		geometry.getElement().insertBefore(pointElement, refPointElement);
		return new PointImpl(create -> refPointElement);
	}
	
	@Override
	public Point add(int index) {
		List<Element> points = DocumentImpl.getChildrenElements(geometry.getElement(), MX_POINT_ELEMENT);
		if (index > points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		
		Element pointElement = geometry.getElement().getOwnerDocument().createElement(MX_POINT_ELEMENT);
		if (index == points.size()) {
			geometry.getElement().appendChild(pointElement);
		} else {
			Element refPointElement = points.get(index);
			geometry.getElement().insertBefore(pointElement, refPointElement);
		}
		
		return new PointImpl(create -> pointElement);
	}

}
