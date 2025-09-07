package org.nasdanika.drawio.message;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage<V> extends ElementMessage<ModelElement,V,BaseProcessor<ModelElement,V>> {

	public ReferrerMessage(ElementMessage<?,V,?> parent, ModelElement target, V value, BaseProcessor<ModelElement,V> processor) {
		super(parent, target, value, processor);
	}

}
