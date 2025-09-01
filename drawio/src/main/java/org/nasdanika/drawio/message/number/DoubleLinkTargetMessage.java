package org.nasdanika.drawio.message.number;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.message.LinkTargetMessage;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class DoubleLinkTargetMessage<T extends LinkTarget> extends LinkTargetMessage<T,Double> {

	public DoubleLinkTargetMessage(Message<? extends ModelElement,Double> parent, T target, Double value) {
		super(parent, target, value);
	}

}
