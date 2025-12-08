package org.nasdanika.drawio.impl;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.PointList;
import org.w3c.dom.Element;

/**
 * Points list which supports remove operation, but not add or set. Although it supports add(int index, double x, double y) and add(double x, double y) operations.
 */
class PointListImpl extends AbstractList<Point> implements PointList {
	
	private static final String MX_POINT_ELEMENT = "mxPoint";

	private static final String AS_ATTRIBUTE = "as";

	private static final String POINTS_ROLE = "points";

	private static final String ARRAY_ELEMENT = "Array";	
	
	private ConnectionImpl connection;

	PointListImpl(ConnectionImpl connection) {
		this.connection = connection;
	}

	@Override
	public int size() {
		Element geometryElement = connection.getGeometryElement(false);
		if (geometryElement == null) {
			return 0;
		}
		List<Element> arrayElements = DocumentImpl.getChildrenElements(geometryElement, ARRAY_ELEMENT, e -> POINTS_ROLE.equals(e.getAttribute(AS_ATTRIBUTE)));
		if (arrayElements.isEmpty()) {
			return 0;
		}
		return DocumentImpl.getChildrenElements(arrayElements.get(0), MX_POINT_ELEMENT).size();		
	}

	@Override
	public Point get(int index) {
		Element geometryElement = connection.getGeometryElement(false);
		if (geometryElement == null) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		List<Element> arrayElements = DocumentImpl.getChildrenElements(geometryElement, ARRAY_ELEMENT, e -> POINTS_ROLE.equals(e.getAttribute(AS_ATTRIBUTE)));
		if (arrayElements.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		List<Element> points = DocumentImpl.getChildrenElements(arrayElements.get(0), MX_POINT_ELEMENT);
		if (index <= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		return new PointImpl(create -> points.get(index));
	}	
	
	@Override
	public Point remove(int index) {
		Element geometryElement = connection.getGeometryElement(false);
		if (geometryElement == null) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		List<Element> arrayElements = DocumentImpl.getChildrenElements(geometryElement, ARRAY_ELEMENT, e -> POINTS_ROLE.equals(e.getAttribute(AS_ATTRIBUTE)));
		if (arrayElements.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		Element arrayElement = arrayElements.get(0);
		List<Element> points = DocumentImpl.getChildrenElements(arrayElement, MX_POINT_ELEMENT);
		if (index >= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		
		Element pointElement = points.get(index);
		arrayElement.removeChild(pointElement);
		
		if (points.size() == 1) {
			// Now empty - remove Array
			geometryElement.removeChild(arrayElement);
		}
		
		return new PointImpl(create -> pointElement);
	}	
	
	@Override
	public Point set(int index, Point element) {
		Element geometryElement = connection.getGeometryElement(true);
		List<Element> arrayElements = DocumentImpl.getChildrenElements(geometryElement, ARRAY_ELEMENT, e -> POINTS_ROLE.equals(e.getAttribute(AS_ATTRIBUTE)));
		if (arrayElements.isEmpty()) {
			Element arrayElement = geometryElement.getOwnerDocument().createElement(ARRAY_ELEMENT);
			geometryElement.appendChild(arrayElement);
			arrayElements = Collections.singletonList(arrayElement);
		}
		Element arrayElement = arrayElements.get(0);
		List<Element> points = DocumentImpl.getChildrenElements(arrayElement, MX_POINT_ELEMENT);
		if (index >= points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		Element pointElement = ((PointImpl) element).elementProvider.apply(false);
		Element refPointElement = points.get(index);
		if (refPointElement == pointElement) {
			return element;
		}
		arrayElement.insertBefore(pointElement, refPointElement);
		return new PointImpl(create -> refPointElement);
	}
	
	@Override
	public Point add(int index, double x, double y) {
		Element geometryElement = connection.getGeometryElement(true);
		List<Element> arrayElements = DocumentImpl.getChildrenElements(geometryElement, ARRAY_ELEMENT, e -> POINTS_ROLE.equals(e.getAttribute(AS_ATTRIBUTE)));
		if (arrayElements.isEmpty()) {
			Element arrayElement = geometryElement.getOwnerDocument().createElement(ARRAY_ELEMENT);
			arrayElement.setAttribute(AS_ATTRIBUTE, POINTS_ROLE);
			geometryElement.appendChild(arrayElement);
			arrayElements = Collections.singletonList(arrayElement);
		}
		Element arrayElement = arrayElements.get(0);
		List<Element> points = DocumentImpl.getChildrenElements(arrayElement, MX_POINT_ELEMENT);
		if (index > points.size()) {
			throw new ArrayIndexOutOfBoundsException(index);			
		}
		
		Element pointElement = arrayElement.getOwnerDocument().createElement(MX_POINT_ELEMENT);
		if (index == points.size()) {
			arrayElement.appendChild(pointElement);
		} else {
			Element refPointElement = points.get(index);
			arrayElement.insertBefore(pointElement, refPointElement);
		}
		
		Point point = new PointImpl(create -> pointElement);
		point.setLocation(x, y);
		return point;
	}

}
