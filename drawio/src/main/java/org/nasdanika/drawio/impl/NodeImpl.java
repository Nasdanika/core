package org.nasdanika.drawio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class NodeImpl extends LayerImpl implements Node {
	
	NodeImpl(Element element, ModelImpl model) {
		super(element, model);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LayerElement> getChildren() {
		return (List) super.getChildren();
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
	
	org.nasdanika.drawio.model.Node toModelNode(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Node mNode = toModelLayer(factory, factory.createNode(), markerFactory, modelElementProvider, tagProvider);
		Rectangle geometry = getGeometry();
		if (geometry != null) {
			org.nasdanika.drawio.model.Geometry mGeometry = factory.createGeometry();
			mGeometry.setX(geometry.getX());
			mGeometry.setY(geometry.getY());
			mGeometry.setHeight(geometry.getHeight());
//			mGeometry.setOffsetPoint(Point);
//			mGeometry.setRelative(boolean);
//			mGeometry.setSourcePoint(Point);
//			mGeometry.setTargetPoint(Point);
			mGeometry.setWidth(geometry.getWidth());
			mNode.setGeometry(mGeometry);
		}
		return mNode;
	}

}
