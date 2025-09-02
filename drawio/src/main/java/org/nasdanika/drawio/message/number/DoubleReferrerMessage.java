package org.nasdanika.drawio.message.number;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.ReferrerMessage;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class DoubleReferrerMessage<T extends ModelElement> extends ReferrerMessage<T,Double> {

	public DoubleReferrerMessage(Message<? extends Element,Double> parent, T target, Double value) {
		super(parent, target, value);
	}

}
