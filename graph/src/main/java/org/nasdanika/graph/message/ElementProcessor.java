package org.nasdanika.graph.message;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ChildEndpoint;
import org.nasdanika.graph.processor.ChildHandler;
import org.nasdanika.graph.processor.ClientHandler;
import org.nasdanika.graph.processor.ParentEndpoint;
import org.nasdanika.graph.processor.ParentHandler;

/**
 * Element processor.
 * Sends a root message and stores it.
 * @param <T> processor element type
 */
public abstract class ElementProcessor<V> {
		
	protected abstract V parentValue(Message<V> message);

	protected abstract V childValue(Message<V> message, Element child);
		
	@ParentHandler(proxy = Consumer.class)
	public void receiveFromParent(Message<V> parentMessage) {
		for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
			V childValue = childValue(parentMessage, ce.getKey());
			if (childValue != null) {
				ce.getValue().accept(parentMessage, childValue);
			}
		}
		
		onParentMessage(parentMessage);
	}
	
	@ParentEndpoint
	public BiConsumer<Message<V>,V> parentEndpoint;
		
	protected Map<Element, BiConsumer<Message<V>,V>> childEndpoints = new ConcurrentHashMap<>();
	
	@ChildEndpoint
	public void setChildEndpoint(Element child, BiConsumer<Message<V>,V> endpoint) {
		childEndpoints.put(child, endpoint);
	}
	
	@ChildHandler
	public Consumer<Message<V>> getChildHandler(Element child) {
		return message -> {
			if (parentEndpoint != null) {
				V parentValue = parentValue(message);
				if (parentValue != null) {
					parentEndpoint.accept(message, parentValue);
				}				
			}
			
			for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
				if (ce.getKey() != child) {
					V childValue = childValue(message, ce.getKey());
					if (childValue != null) {
						ce.getValue().accept(message, childValue);
					}
				}
			}		
			
			onChildMessage(child, message);
		};
	}
		
	@ClientHandler
	public Consumer<Message<V>> getClientHandler(Object clientKey) {
		return message -> {
			if (parentEndpoint != null) {
				V parentValue = parentValue(message);
				if (parentValue != null) {
					parentEndpoint.accept(message, parentValue);
				}				
			}
			
			for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
				V childValue = childValue(message, ce.getKey());
				if (childValue != null) {
					ce.getValue().accept(message, childValue);
				}
			}	
			
			onClientMessage(clientKey, message);
		};
	}	
	
	protected void onClientMessage(Object clientKey, Message<V> message) {
		
	}	
	
	protected void onParentMessage(Message<V> message) {
		
	}
	
	protected void onChildMessage(Element child, Message<V> message) {
		
	}
	
}
