package org.nasdanika.drawio.message;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.processor.HandlerType;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage<V> extends Message<V> {

	protected ReferrerMessage(HandlerType type, Element target, V value) {
		super(type, target, value);
	}

	protected ReferrerMessage(HandlerType type, Message<V> parent, Element target, V value) {
		super(type, parent, target, value);
	}

}
