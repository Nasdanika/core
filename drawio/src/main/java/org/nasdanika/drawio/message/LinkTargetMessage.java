package org.nasdanika.drawio.message;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.processor.HandlerType;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class LinkTargetMessage<V> extends Message<V> {

	protected LinkTargetMessage(HandlerType type, Element target, V value) {
		super(type, target, value);
	}

	protected LinkTargetMessage(HandlerType type, Message<V> parent, Element target, V value) {
		super(type, parent, target, value);
	}

}
