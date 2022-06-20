package org.nasdanika.drawio.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.w3c.dom.Element;

class NodeImpl extends ModelElementImpl implements Node {

	NodeImpl(Element element, ModelImpl model) {
		super(element, model);
	}

	@Override
	public List<Connection> getInboundConnections() {
		Predicate<ModelElement> predicate = pe -> element.hasAttribute(ATTRIBUTE_ID) && pe.getElement().hasAttribute(ATTRIBUTE_TARGET) && pe.getElement().getAttribute(ATTRIBUTE_TARGET).equals(element.getAttribute(ATTRIBUTE_ID));
		return model.collect(predicate).stream().filter(Connection.class::isInstance).map(Connection.class::cast).collect(Collectors.toList());
	}

	@Override
	public List<Connection> getOutboundConnections() {
		Predicate<ModelElement> predicate = pe -> element.hasAttribute(ATTRIBUTE_ID) && pe.getElement().hasAttribute(ATTRIBUTE_SOURCE) && pe.getElement().getAttribute(ATTRIBUTE_SOURCE).equals(element.getAttribute(ATTRIBUTE_ID));
		return model.collect(predicate).stream().filter(Connection.class::isInstance).map(Connection.class::cast).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ModelElement> getChildren() {
		return (List<ModelElement>) super.getChildren();
	}

}
