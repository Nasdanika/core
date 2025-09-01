package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;

/**
 * Message sent from a child element to its parent. 
 * For example, from a {@link Node} to its containing {@link Layer}
 * or from a {@link Page} to {@link Document}
 */
public class ParentMessage<T extends Element,V> extends Message<T,V> {

	public ParentMessage(Message<? extends Element,V> parent, T target, V value) {
		super(parent, target, value);
	}

}
