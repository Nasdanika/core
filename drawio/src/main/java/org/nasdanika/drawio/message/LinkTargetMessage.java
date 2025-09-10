package org.nasdanika.drawio.message;

import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.graph.message.ElementMessage;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class LinkTargetMessage<T extends LinkTarget,V,P extends LinkTargetProcessor<T,V>> extends ElementMessage<T,V,P> {

	public LinkTargetMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value, processor);
	}

}
