package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;

/**
 * Message sent from a link target to the referrer. 
 * For example, from a {@link Page} to a {@link ModelElement} linking to this page.
 */
public class ReferrerMessage<T extends ModelElement,V> extends Message<T,V> {

	public ReferrerMessage(Message<? extends Element,V> parent, T target, V value) {
		super(parent, target, value);
	}

}
