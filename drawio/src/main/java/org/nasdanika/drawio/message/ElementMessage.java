package org.nasdanika.drawio.message;

import java.util.List;

import org.nasdanika.common.message.Message;
import org.nasdanika.common.message.ProcessorMessage;
import org.nasdanika.drawio.Element;

/**
 * 
 * @param <T> Message target type
 * @param <V> Message value type
 * @param <P> Message processor type
 */
public class ElementMessage<T extends Element,V,P extends BaseProcessor<T,V>> extends Message<T,V> implements ProcessorMessage<ElementMessage<?,V,?>> {

	protected P processor;

	protected ElementMessage(ElementMessage<?,V,?> parent, T target, V value, P processor) {
		super(parent, target, value);
		this.processor = processor;
	}

	protected ElementMessage(T target, V value, P processor) {
		super(target, value);
		this.processor = processor;
	}

	public List<ElementMessage<?, V, ?>> process() {
		return processor.processMessage(this);
	}
	
}
