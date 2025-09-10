package org.nasdanika.graph.message;

import java.util.List;

import org.nasdanika.graph.Element;

/**
 * 
 * @param <T> Message target type
 * @param <V> Message value type
 * @param <P> Message processor type
 */
public class ElementMessage<T extends Element,V,P extends ElementProcessor<T,V>> extends Message<T,V> implements ProcessorMessage<ElementMessage<?,V,?>> {

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
