package org.nasdanika.drawio.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.w3c.dom.Element;

class RootImpl extends ModelElementImpl implements Root {

	RootImpl(Element element, ModelImpl model) {
		super(element, model);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Layer> getChildren() {
		return (List) super.getChildren();
	}

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

	@Override
	public URI getURI() {
		URI modelURI = getModel().getURI();
		return modelURI == null ? URI.createURI(getId()) : modelURI.appendSegment(URLEncoder.encode(getId(), StandardCharsets.UTF_8));
	}

}
