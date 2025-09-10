package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.BaseProcessor;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ParentMessage;

/**
 * Message sent from a child element to its parent. 
 * For example, from a {@link Node} to its containing {@link Layer}
 * or from a {@link Page} to {@link Document}
 */
public class DoubleParentMessage<T extends Element,P extends BaseProcessor<T,Double>> extends ParentMessage<T,Double,P> {

	public DoubleParentMessage(ElementMessage<?,Double,?> parent, T target, Double value, P processor) {
		super(parent, target, value, processor);
	}

}
