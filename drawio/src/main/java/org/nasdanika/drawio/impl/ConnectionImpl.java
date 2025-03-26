package org.nasdanika.drawio.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class ConnectionImpl extends ModelElementImpl implements Connection {

	private static final String SOURCE_POINT_ROLE = "sourcePoint";
	private static final String TARGET_POINT_ROLE = "targetPoint";
	private static final String MX_POINT_ATTRIBUTE = "mxPoint";

	ConnectionImpl(Element element, ModelImpl model) {
		super(element, model);
	}

	@Override
	public Node getSource() {
		return (Node) model.find(getCellElement().getAttribute(ATTRIBUTE_SOURCE));
	}

	@Override
	public Node getTarget() {
		return (Node) model.find(getCellElement().getAttribute(ATTRIBUTE_TARGET));
	}
	
	protected ModelElement getLogicalParent(ConnectionBase connectionBase) {
		if (connectionBase == null) {
			return getParent();
		}
		
		switch (connectionBase) {
		case PARENT:
			return getParent();
		case SOURCE:
			return getSource();
		case TARGET:
			return getTarget();
		default:
			throw new UnsupportedOperationException("Unsupported connection base: " + connectionBase);		
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getSource() + " -> " + getTarget();
	}
	
	org.nasdanika.drawio.model.Connection toModelConnection(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Connection mConnection = toModelElement(factory.createConnection(), markerFactory, modelElementProvider, tagProvider);
		
		Node sourceNode = getSource();
		if (sourceNode != null) {
			modelElementProvider.apply(sourceNode).thenAccept(sn -> mConnection.setSource((org.nasdanika.drawio.model.Node) sn));
		}
		
		Node targetNode = getTarget();
		if (targetNode != null) {
			modelElementProvider.apply(targetNode).thenAccept(tn -> mConnection.setTarget((org.nasdanika.drawio.model.Node) tn));
		}
		
		return mConnection;
	}

	@Override
	public void setSource(Node node) {
		if (node == null) {
			getCellElement().removeAttribute(ATTRIBUTE_SOURCE);
		} else {
			getCellElement().setAttribute(ATTRIBUTE_SOURCE, node.getId());
		}
	}

	@Override
	public void setTarget(Node node) {
		if (node == null) {
			getCellElement().removeAttribute(ATTRIBUTE_TARGET);
		} else {
			getCellElement().setAttribute(ATTRIBUTE_TARGET, node.getId());
		}
	}
	
	protected org.w3c.dom.Element getSourcePointElement(boolean create) {
		Element geometryElement = getGeometryElement(true);
		List<org.w3c.dom.Element> sourcePointElements = DocumentImpl.getChildrenElements(
				geometryElement, 
				MX_POINT_ATTRIBUTE,
				pe -> SOURCE_POINT_ROLE.equals(pe.getAttribute(AS_ATTRIBUTE)));
		
		if (sourcePointElements.isEmpty()) {
			if (create) {
				org.w3c.dom.Element ret = element.getOwnerDocument().createElement(MX_POINT_ATTRIBUTE);
				ret.setAttribute(AS_ATTRIBUTE, SOURCE_POINT_ROLE);
				geometryElement.appendChild(ret);
				return ret;
			}
		} 
		
		if (sourcePointElements.size() == 1) {
			return sourcePointElements.get(0);
		} 
		
		throw new IllegalArgumentException("Expected one source point element, got " + sourcePointElements.size());
	}	
		
	protected org.w3c.dom.Element getTargetPointElement(boolean create) {
		Element geometryElement = getGeometryElement(true);
		List<org.w3c.dom.Element> targetPointElements = DocumentImpl.getChildrenElements(
				geometryElement, 
				MX_POINT_ATTRIBUTE,
				pe -> TARGET_POINT_ROLE.equals(pe.getAttribute(AS_ATTRIBUTE)));
		
		if (targetPointElements.isEmpty()) {
			if (create) {
				org.w3c.dom.Element ret = element.getOwnerDocument().createElement(MX_POINT_ATTRIBUTE);
				ret.setAttribute(AS_ATTRIBUTE, TARGET_POINT_ROLE);
				geometryElement.appendChild(ret);
				return ret;
			}
		} 
		
		if (targetPointElements.size() == 1) {
			return targetPointElements.get(0);
		} 
		
		throw new IllegalArgumentException("Expected one target point element, got " + targetPointElements.size());
	}	

	@Override
	public Point setSourcePoint(double x, double y) {
		Point ret = getSourcePoint();
		ret.setX(x);
		ret.setY(y);
		return ret;		
	}

	@Override
	public Point getSourcePoint() {
		return new PointImpl(this::getSourcePointElement);
	}

	@Override
	public Point setTargetPoint(double x, double y) {
		Point ret = getTargetPoint();
		ret.setX(x);
		ret.setY(y);
		return ret;		
	}

	@Override
	public Point getTargetPoint() {
		return new PointImpl(this::getTargetPointElement);
	}			

}
