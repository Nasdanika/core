package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.gen.section.BaseProcessor;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage extends Message<BaseProcessor<?>> {

	public ReferrerMessage(Message<?> parent, BaseProcessor<?> processor) {
		super(parent, processor);
	}

}
