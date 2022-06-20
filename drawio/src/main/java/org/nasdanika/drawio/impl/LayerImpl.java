package org.nasdanika.drawio.impl;

import java.util.List;

import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.ModelElement;
import org.w3c.dom.Element;

class LayerImpl extends ModelElementImpl implements Layer {

	LayerImpl(Element element, ModelImpl model) {
		super(element, model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelElement> getElements() {
		return (List<ModelElement>) getChildren();
	}

}
