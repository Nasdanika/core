package org.nasdanika.drawio.message;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.nasdanika.drawio.Element;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.Registry;

/**
 * Base class for message processors.
 * Sends a root message and stores it.
 */
public abstract class BaseProcessor<T extends Element,V> {
		
	protected T element;
	
	@ProcessorElement	
	public void setElement(T element) {
		this.element = element;
	}
	
	@Registry 
	public Map<Element, ProcessorInfo<BaseProcessor<? extends Element, V>>> registry;

	/**
	 * Processes a message, returns a list of messages which were sent as a result of processing
	 * @param message
	 * @return
	 */
	public abstract List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message);
	
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
