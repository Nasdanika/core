package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.BaseProcessor;
import org.nasdanika.graph.message.ChildMessage;
import org.nasdanika.graph.message.ElementMessage;

/**
 * Message sent from a parent (container) to it child. For example from {@link Document} to {@link Page}
 * or from {@link Layer} to {@link Node}.
 */
public class DoubleChildMessage<T extends Element, P extends BaseProcessor<T,Double>> extends ChildMessage<T,Double,P> {

	public DoubleChildMessage(ElementMessage<?,Double,?> parent, T target, Double value, P processor) {
		super(parent, target, value, processor);
	}

}
