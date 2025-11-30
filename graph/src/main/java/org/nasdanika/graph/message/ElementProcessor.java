package org.nasdanika.graph.message;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ChildEndpoint;
import org.nasdanika.graph.processor.ChildHandler;
import org.nasdanika.graph.processor.ClientEndpoint;
import org.nasdanika.graph.processor.ClientHandler;
import org.nasdanika.graph.processor.ParentEndpoint;
import org.nasdanika.graph.processor.ParentHandler;

/**
 * Element processor.
 * Sends a root message and stores it.
 * @param <T> processor element type
 */
public abstract class ElementProcessor<V,K> {
		
	protected abstract V parentValue(Message<V> message);

	protected abstract V childValue(Message<V> message, Element child);

	protected abstract V clientValue(Message<V> message, K clientKey);
		
	@ParentHandler(proxy = Consumer.class)
	public void receiveFromParent(Message<V> parentMessage) {
		for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
			V childValue = childValue(parentMessage, ce.getKey());
			if (childValue != null) {
				ce.getValue().accept(parentMessage, childValue);
			}
		}
		
		for (Entry<K, BiConsumer<Message<V>, V>> ce: clientEndpoints.entrySet()) {
			V clientValue = clientValue(parentMessage, ce.getKey());
			if (clientValue != null) {
				ce.getValue().accept(parentMessage, clientValue);
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
			
			for (Entry<K, BiConsumer<Message<V>, V>> ce: clientEndpoints.entrySet()) {
				V clientValue = clientValue(message, ce.getKey());
				if (clientValue != null) {
					ce.getValue().accept(message, clientValue);
				}
			}					
			
			onChildMessage(child, message);
		};
	}
	
	protected Map<K, BiConsumer<Message<V>,V>> clientEndpoints = new ConcurrentHashMap<>();
	
	@ClientEndpoint
	public void setClientEndpoint(K clientKey, BiConsumer<Message<V>,V> endpoint) {
		clientEndpoints.put(clientKey, endpoint);
	}
		
	@ClientHandler
	public Consumer<Message<V>> getClientHandler(K clientKey) {
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
			
			for (Entry<K, BiConsumer<Message<V>, V>> ce: clientEndpoints.entrySet()) {
				if (ce.getKey() != clientKey) {
					V clientValue = clientValue(message, ce.getKey());
					if (clientValue != null) {
						ce.getValue().accept(message, clientValue);
					}
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
