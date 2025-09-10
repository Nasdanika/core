package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.message.LinkTargetMessage;
import org.nasdanika.drawio.message.LinkTargetProcessor;
import org.nasdanika.graph.message.ElementMessage;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class DoubleLinkTargetMessage<T extends LinkTarget,P extends LinkTargetProcessor<T,Double>> extends LinkTargetMessage<T,Double,P> {

	public DoubleLinkTargetMessage(ElementMessage<?,Double,?> parent, T target, Double value, P processor) {
		super(parent, target, value, processor);
	}

}
