package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.gen.section.BaseProcessor;

/**
 * Message sent from a child element to its parent. 
 * For example, from a {@link Node} to its containing {@link Layer}
 * or from a {@link Page} to {@link Document}
 */
public class ParentMessage extends Message<BaseProcessor<?>> {

	public ParentMessage(Message<?> parent, BaseProcessor<?> processor) {
		super(parent, processor);
	}

}
