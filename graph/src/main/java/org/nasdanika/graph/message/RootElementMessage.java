package org.nasdanika.graph.message;

import org.nasdanika.graph.Element;

/**
 * Root message without a parent.
 */
public class RootElementMessage<T extends Element,V,P extends ElementProcessor<T,V>> extends ElementMessage<T,V,P> {

	public RootElementMessage(P processor, V value) {
		super(processor, value);
	}

}
