package org.nasdanika.drawio;

public interface LayerElement extends ModelElement {
	
	default Layer getLayer() {
		ModelElement parent = getParent();
		if (parent instanceof Layer) {
			return (Layer) parent;
		}
		if (parent instanceof LayerElement) {
			return ((LayerElement) parent).getLayer();
		}
			
		return null;	
	}

}
