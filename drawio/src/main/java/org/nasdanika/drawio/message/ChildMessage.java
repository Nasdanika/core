package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;

/**
 * Message sent from a parent (container) to it child. For example from {@link Document} to {@link Page}
 * or from {@link Layer} to {@link Node}.
 */
public class ChildMessage<T extends Element,V,P extends BaseProcessor<T,V>> extends ElementMessage<T,V,P> {

	public ChildMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value, processor);
	}

}
