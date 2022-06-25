package org.nasdanika.drawio.impl;

import java.util.List;
import java.util.UUID;

import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.w3c.dom.Element;

class RootImpl extends ModelElementImpl implements Root {

	RootImpl(Element element, ModelImpl model) {
		super(element, model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Layer> getLayers() {
		return (List<Layer>) getChildren();
	}

	@Override
	public Layer createLayer() {
		Element layerElement = element.getOwnerDocument().createElement("mxCell");
		layerElement.setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString());
		layerElement.setAttribute(ATTRIBUTE_PARENT, element.getAttribute(ATTRIBUTE_ID));
		element.getParentNode().appendChild(layerElement);
		List<Layer> layers = getLayers();
		return layers.get(layers.size() - 1);
	}

}
