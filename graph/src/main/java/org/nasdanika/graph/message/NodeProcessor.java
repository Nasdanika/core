package org.nasdanika.graph.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.OutgoingHandler;

public abstract class NodeProcessor<T extends Node, V> extends ElementProcessor<T, V> {

	protected abstract V sourceValue(V messageValue, Connection outgoingConnection);
	
	protected abstract V targetValue(V messageValue, Connection incomingConnection);
	
	/**
	 * Creates 
	 * @param connection
	 * @return
	 */
	@OutgoingHandler
	public <C extends Connection> Function<IncomingConnectionMessage<C, V>, SourceMessage<C,T,V>> getOutgoingHandler(C outgoingConnection) {
		return message -> {
			if (message.hasSeen(getElement())) {
				return null;
			}
			V sourceValue = sourceValue(message.getValue(), outgoingConnection);
			if (sourceValue == null) {
				return null;
			}
			return new SourceMessage<C,T,V>(message, getElement(), sourceValue, this);
		};
	}		
		
	@IncomingHandler
	public <C extends Connection> Function<OutgoingConnectionMessage<C, V>, TargetMessage<C,T,V>> getIncomingHandler(C incomingConnection) {
		return message -> {
			if (message.hasSeen(getElement())) {
				return null;
			}
			V targetValue = targetValue(message.getValue(), incomingConnection);
			if (targetValue == null) {
				return null;
			}
			return new TargetMessage<C,T,V>(message, getElement(), targetValue, this);
		};
	}
		
	protected Collection<Function<ElementMessage<?, V, ?>, OutgoingConnectionMessage<?,V>>> outgoingEndpoints = Collections.synchronizedList(new ArrayList<>());
		
	@OutgoingEndpoint
	public void addOutgoingEndpoints(Function<ElementMessage<?, V, ?>, OutgoingConnectionMessage<?,V>> outgoingEndpoint) {
		outgoingEndpoints.add(outgoingEndpoint);
	}
	
	protected Collection<Function<ElementMessage<?, V, ?>, IncomingConnectionMessage<?,V>>> incomingEndpoints = Collections.synchronizedList(new ArrayList<>());	
		
	@IncomingEndpoint
	public void addIncomingEndpoints(Function<ElementMessage<?, V, ?>, IncomingConnectionMessage<?,V>> incomingEndpoint) {
		incomingEndpoints.add(incomingEndpoint);
	}	
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
		List<ElementMessage<?, V, ?>> messages = super.processMessage(message);
		for (Function<ElementMessage<?, V, ?>, OutgoingConnectionMessage<?, V>> oe: outgoingEndpoints) {
			OutgoingConnectionMessage<?, V> om = oe.apply(message);
			if (om != null) {
				messages.add(om);
			}
		}
		
		for (Function<ElementMessage<?, V, ?>, IncomingConnectionMessage<?, V>> ie: incomingEndpoints) {
			IncomingConnectionMessage<?,V> im = ie.apply(message);
			if (im != null) {
				messages.add(im);
			}
		}		
		
		return messages;
	}	

}
