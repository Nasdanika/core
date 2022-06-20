package org.nasdanika.drawio.impl;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
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

}
