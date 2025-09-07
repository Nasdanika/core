package org.nasdanika.drawio.message;

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
public class ParentMessage<T extends Element,V,P extends BaseProcessor<T,V>> extends ElementMessage<T,V,P> {

	public ParentMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value, processor);
	}

}
