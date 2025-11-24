package org.nasdanika.drawio.message;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ElementProcessor;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage<V> extends ElementMessage<ModelElement,V,ElementProcessor<ModelElement,V>> {

	public ReferrerMessage(ElementMessage<?,V,?> parent, ElementProcessor<ModelElement,V> processor, V value) {
		super(parent, processor, value);
	}

}
