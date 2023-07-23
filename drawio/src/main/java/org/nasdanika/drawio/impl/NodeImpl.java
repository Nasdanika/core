package org.nasdanika.drawio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Rectangle;
import org.w3c.dom.Element;

class NodeImpl extends LayerImpl implements Node {
	
	NodeImpl(Element element, ModelImpl model) {
		super(element, model);
	}

	@Override
	public List<Connection> getIncomingConnections() {
		Predicate<org.w3c.dom.Element> predicate = me -> element.hasAttribute(ATTRIBUTE_ID) 
				&& getCellElement(me).hasAttribute(ATTRIBUTE_TARGET) 
				&& getCellElement(me).getAttribute(ATTRIBUTE_TARGET).equals(element.getAttribute(ATTRIBUTE_ID));
		
		return model.collect(predicate).stream().filter(Connection.class::isInstance).map(Connection.class::cast).toList();
	}

	@Override
	public List<Connection> getOutgoingConnections() {
		Predicate<org.w3c.dom.Element> predicate = 
				me -> element.hasAttribute(ATTRIBUTE_ID) 
				&& getCellElement(me).hasAttribute(ATTRIBUTE_SOURCE) 
				&& getCellElement(me).getAttribute(ATTRIBUTE_SOURCE).equals(element.getAttribute(ATTRIBUTE_ID));
				
		return model.collect(predicate).stream().filter(Connection.class::isInstance).map(Connection.class::cast).toList();
	}
	
	private Element getGeometryElement(boolean create) {
		Element cellElement = getCellElement();
		List<org.w3c.dom.Element> geometryElements = DocumentImpl.getChildrenElements(cellElement, "mxGeometry");
		if (geometryElements.isEmpty()) {
			if (create) {
				Element ret = element.getOwnerDocument().createElement("mxGeometry");
				ret.setAttribute("as", "geometry");
				cellElement.appendChild(ret);
				return ret;
			}
		} 
		
		if (geometryElements.size() == 1) {
			return geometryElements.get(0);
		} 
		
		throw new IllegalArgumentException("Expected one geometry element, got " + geometryElements.size());
	}

	@Override
	public Rectangle getGeometry() {
		return new RectangleImpl(this::getGeometryElement);
	}

	@Override
	protected List<? extends org.nasdanika.drawio.Element> getLogicalChildren(ConnectionBase connectionBase) {
		List<org.nasdanika.drawio.Element> logicalChildren = new ArrayList<>(super.getLogicalChildren(connectionBase));
		if (connectionBase == ConnectionBase.SOURCE) {
			logicalChildren.addAll(getOutgoingConnections());
		} else if (connectionBase == ConnectionBase.TARGET) {
			logicalChildren.addAll(getIncomingConnections());			
		}
		return logicalChildren;
	}

}
