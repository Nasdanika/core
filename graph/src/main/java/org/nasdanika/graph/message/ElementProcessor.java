package org.nasdanika.graph.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.Registry;

/**
 * Element processor.
 * Sends a root message and stores it.
 * @param <T> processor element type
 */
public abstract class ElementProcessor<T extends Element,V> {
		
	protected T element;
	
	@ProcessorElement	
	public void setElement(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return element;
	}
	
	@Registry 
	public Map<Element, ProcessorInfo<ElementProcessor<Element, V>>> registry;
	
	@ChildProcessors
	public Map<Element, ProcessorInfo<ElementProcessor<Element, V>>> children;

	/**
	 * Processes a message, returns a list of messages which were sent as a result of processing
	 * @param message
	 * @return
	 */
	public Collection<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
		List<ElementMessage<?, V, ?>> ret = new ArrayList<>();
		for (ProcessorInfo<ElementProcessor<Element, V>> childProcessorInfo: children.values()) {
			Element child = childProcessorInfo.getElement();
			if (!message.hasSeen(child)) {
				if (childProcessorInfo.getProcessor() != null) {
					V childValue = childValue(message.getValue(), child);
					if (childValue != null) {
						ret.add(
								new ChildMessage<Element,V,ElementProcessor<Element,V>>(
										message, 
										child, 
										childValue, 
										childProcessorInfo.getProcessor()));
					}
				}
			}
		}
		
		return ret;
	}

	protected abstract V childValue(V messageValue, Element child);
	
	private RootElementMessage<T,V,?> rootMessage;	
	
	/**
	 * Sends messages to releated elements to establish traceability.
	 * Processes breadth first by using a queue
	 */
	public RootElementMessage<T,V,?> sendMessages(V value) {		
		Queue<ElementMessage<?,V,?>> processingQueue = new ConcurrentLinkedQueue<>();
		rootMessage = new RootElementMessage<>(element, value, this);
		
		processingQueue.add(rootMessage);		
		
		ElementMessage<?,V,?> message;
		while ((message = processingQueue.poll()) != null) {
			for (ElementMessage<?,V,?> child: message.process()) {
				processingQueue.add(child);
			}
		}
		
		return rootMessage;
	}	
	
	public RootElementMessage<T,V,?> getRootMessage() {
		return rootMessage;
	}
	
}
