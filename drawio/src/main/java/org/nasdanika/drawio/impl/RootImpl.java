package org.nasdanika.drawio.impl;

import java.util.List;

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

}
