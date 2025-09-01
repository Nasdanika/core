package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.gen.section.BaseProcessor;

/**
 * Message sent from a parent (container) to it child. For example from {@link Document} to {@link Page}
 * or from {@link Layer} to {@link Node}.
 */
public class ChildMessage extends Message<BaseProcessor<?>> {

	public ChildMessage(Message<?> parent, BaseProcessor<?> processor) {
		super(parent, processor);
	}

}
