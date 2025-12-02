package org.nasdanika.drawio;

/**
 * Layer element - {@link Node} or {@link Connection}
 * @author Pavel
 *
 */
public interface LayerElement<L extends LayerElement<L>> extends ModelElement<L> {
	
	/**
	 * Containing layer.
	 * @return
	 */
	default Layer<?> getLayer() {
		ModelElement<?> parent = getParent();
		if (parent instanceof Layer) {
			return (Layer<?>) parent;
		}
		if (parent instanceof LayerElement) {
			return ((LayerElement<?>) parent).getLayer();
		}
			
		return null;	
	}

}
