package org.nasdanika.graph.message;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;

public abstract class NodeProcessor<V> extends ElementProcessor<V> {

	protected abstract V outgoingValue(Message<V> message, Connection outgoingConnection);
	
	protected abstract V incomingValue(Message<V> message, Connection incomingConnection);
		
	protected Map<Connection, BiConsumer<Message<V>,V>> incomingEndpoints = new ConcurrentHashMap<>();
	
	@IncomingEndpoint
	public void setIncomingEndpoint(Connection incomingConnection, BiConsumer<Message<V>,V> endpoint) {
		incomingEndpoints.put(incomingConnection, endpoint);
	}
	
	@IncomingHandler
	public Consumer<Message<V>> getIncomingHandler(Connection incomingConnection) {
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
			
			for (Entry<Connection, BiConsumer<Message<V>, V>> ie: incomingEndpoints.entrySet()) {
				if (ie.getKey() != incomingConnection) {
					V incomingValue = incomingValue(message, ie.getKey());
					if (incomingValue != null) {
						ie.getValue().accept(message, incomingValue);
					}
				}
			}		
			
			for (Entry<Connection, BiConsumer<Message<V>, V>> oe: outgoingEndpoints.entrySet()) {
				V outgoingValue = outgoingValue(message, oe.getKey());
				if (outgoingValue != null) {
					oe.getValue().accept(message, outgoingValue);
				}
			}		
			
			onIncomingMessage(incomingConnection, message);
		};
	}
		
	protected void onIncomingMessage(Connection incomingConnection, Message<V> message) {
		
	}
	
	protected Map<Connection, BiConsumer<Message<V>,V>> outgoingEndpoints = new ConcurrentHashMap<>();
	
	@IncomingEndpoint
	public void setOutgoingEndpoint(Connection outgoingConnection, BiConsumer<Message<V>,V> endpoint) {
		outgoingEndpoints.put(outgoingConnection, endpoint);
	}
	
	@IncomingHandler
	public Consumer<Message<V>> getOutgoingHandler(Connection outgoingConnection) {
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
			
			for (Entry<Connection, BiConsumer<Message<V>, V>> ie: incomingEndpoints.entrySet()) {
				V incomingValue = incomingValue(message, ie.getKey());
				if (incomingValue != null) {
					ie.getValue().accept(message, incomingValue);
				}
			}		
			
			for (Entry<Connection, BiConsumer<Message<V>, V>> oe: outgoingEndpoints.entrySet()) {
				if (oe.getKey() != outgoingConnection) {
					V outgoingValue = outgoingValue(message, oe.getKey());
					if (outgoingValue != null) {
						oe.getValue().accept(message, outgoingValue);
					}
				}
			}		
			
			onOutgoingMessage(outgoingConnection, message);
		};
	}
		
	protected void onOutgoingMessage(Connection outgoingConnection, Message<V> message) {
		
	}

	@Override
	protected void onChildMessage(Element child, Message<V> message) {
		super.onChildMessage(child, message);
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> ie: incomingEndpoints.entrySet()) {
			V incomingValue = incomingValue(message, ie.getKey());
			if (incomingValue != null) {
				ie.getValue().accept(message, incomingValue);
			}
		}		
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> oe: outgoingEndpoints.entrySet()) {
			V outgoingValue = outgoingValue(message, oe.getKey());
			if (outgoingValue != null) {
				oe.getValue().accept(message, outgoingValue);
			}
		}		
	}
	
	@Override
	protected void onClientMessage(Object clientKey, Message<V> message) {
		super.onClientMessage(clientKey, message);
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> ie: incomingEndpoints.entrySet()) {
			V incomingValue = incomingValue(message, ie.getKey());
			if (incomingValue != null) {
				ie.getValue().accept(message, incomingValue);
			}
		}		
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> oe: outgoingEndpoints.entrySet()) {
			V outgoingValue = outgoingValue(message, oe.getKey());
			if (outgoingValue != null) {
				oe.getValue().accept(message, outgoingValue);
			}
		}		
	}
	
	@Override
	protected void onParentMessage(Message<V> message) {
		super.onParentMessage(message);
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> ie: incomingEndpoints.entrySet()) {
			V incomingValue = incomingValue(message, ie.getKey());
			if (incomingValue != null) {
				ie.getValue().accept(message, incomingValue);
			}
		}		
		
		for (Entry<Connection, BiConsumer<Message<V>, V>> oe: outgoingEndpoints.entrySet()) {
			V outgoingValue = outgoingValue(message, oe.getKey());
			if (outgoingValue != null) {
				oe.getValue().accept(message, outgoingValue);
			}
		}		
	}

}
