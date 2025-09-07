package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.BaseProcessor;
import org.nasdanika.drawio.message.ElementMessage;
import org.nasdanika.drawio.message.ReferrerMessage;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class DoubleReferrerMessage extends ReferrerMessage<Double> {

	public DoubleReferrerMessage(ElementMessage<?,Double,?> parent, ModelElement target, Double value, BaseProcessor<ModelElement,Double> processor) {
		super(parent, target, value, processor);
	}

}
