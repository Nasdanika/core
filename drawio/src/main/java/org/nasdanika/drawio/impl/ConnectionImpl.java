package org.nasdanika.drawio.impl;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class ConnectionImpl extends ModelElementImpl implements Connection {

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
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider) {
		org.nasdanika.drawio.model.Connection mConnection = toModelElement(factory.createConnection(), markerFactory, modelElementProvider);
		
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
	

}
