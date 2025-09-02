package org.nasdanika.drawio.message;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage<T extends ModelElement,V,P extends BaseProcessor<T,V>> extends ElementMessage<T,V,P> {

	public ReferrerMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value, processor);
	}

}
