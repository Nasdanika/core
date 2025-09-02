package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Element;

/**
 * Root message without a parent.
 */
public class RootElementMessage<T extends Element,V,P extends BaseProcessor<T,V>> extends ElementMessage<T,V,P> {

	public RootElementMessage(T target, V value, P processor) {
		super(target, value, processor);
	}

}
