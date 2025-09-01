package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.gen.section.BaseProcessor;

/**
 * A message to a link target element from its source.
 * For example, a link from a node to a page. 
 */
public class LinkTargetMessage extends Message<BaseProcessor<?>> {

	public LinkTargetMessage(Message<?> parent, BaseProcessor<?> processor) {
		super(parent, processor);
	}

}
