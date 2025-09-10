package org.nasdanika.graph.message;

import org.nasdanika.graph.Element;

/**
 * Message sent from a parent (container) to it child. 
 */
public class ChildMessage<T extends Element,V,P extends ElementProcessor<T,V>> extends ElementMessage<T,V,P> {

	public ChildMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value, processor);
	}

}
