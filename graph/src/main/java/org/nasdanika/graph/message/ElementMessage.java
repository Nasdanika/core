package org.nasdanika.graph.message;

import java.util.Collection;

import org.nasdanika.graph.Element;

/**
 * 
 * @param <T> Message target type
 * @param <V> Message value type
 * @param <P> Message processor type
 */
public class ElementMessage<T extends Element,V,P extends ElementProcessor<T,V>> extends Message<T,V> implements ProcessorMessage<ElementMessage<?,V,?>> {

	protected P processor;

	protected ElementMessage(ElementMessage<?,V,?> parent, P processor, V value) {
		super(parent, processor.getElement(), value);
		this.processor = processor;
	}

	protected ElementMessage(P processor, V value) {
		super(processor.getElement(), value);
		this.processor = processor;
	}

	public Collection<ElementMessage<?, V, ?>> process() {
		return processor.processMessage(this);
	}
	
}
