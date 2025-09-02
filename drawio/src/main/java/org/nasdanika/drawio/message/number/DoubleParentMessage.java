package org.nasdanika.drawio.message.number;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.ParentMessage;

/**
 * Message sent from a child element to its parent. 
 * For example, from a {@link Node} to its containing {@link Layer}
 * or from a {@link Page} to {@link Document}
 */
public class DoubleParentMessage<T extends Element> extends ParentMessage<T,Double> {

	public DoubleParentMessage(Message<? extends Element,Double> parent, T target, Double value) {
		super(parent, target, value);
	}

}
