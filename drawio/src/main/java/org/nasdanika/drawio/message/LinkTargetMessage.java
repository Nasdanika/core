package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class LinkTargetMessage<T extends LinkTarget,V> extends Message<T,V> {

	public LinkTargetMessage(Message<? extends Element,V> parent, T target, V value) {
		super(parent, target, value);
	}

}
