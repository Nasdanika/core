package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.gen.section.BaseProcessor;

/**
 * Root message sent by a diagram element processor to establish traceability to other elements.
 */
public class RootMessage extends Message<BaseProcessor<?>> {

	public RootMessage(BaseProcessor<?> sender) {
		super(sender);
	}

}
