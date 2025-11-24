package org.nasdanika.graph.message;

import org.nasdanika.graph.Element;

/**
 * Message sent from a child element to its parent. 
 */
public class ParentMessage<T extends Element,V,P extends ElementProcessor<T,V>> extends ElementMessage<T,V,P> {

	public ParentMessage(ElementMessage<?,V,?> parent, P processor, V value) {
		super(parent, processor, value);
	}

}
