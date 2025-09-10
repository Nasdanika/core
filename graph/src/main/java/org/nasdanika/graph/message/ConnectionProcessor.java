package org.nasdanika.graph.message;

import java.util.Collection;
import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.SourceEndpoint;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.graph.processor.TargetEndpoint;
import org.nasdanika.graph.processor.TargetHandler;

public abstract class ConnectionProcessor<T extends Connection, V> extends ElementProcessor<T, V> {

	protected abstract V incomingValue(V messageValue);
	
	protected abstract V outgoingValue(V messageValue);
			
	/**
	 * Creates target message for a given parent message
	 */
	@TargetEndpoint
	public Function<OutgoingConnectionMessage<T, V>, TargetMessage<T, ?, V>> targetEndpoint;
	
	/**
	 * Creates source message for a given target message
	 */
	@SourceEndpoint
	public Function<IncomingConnectionMessage<T, V>, SourceMessage<T, ?, V>> sourceEndpoint;
	
	/**
	 * Creates in incoming connection message for a given parent message
	 */
	@SourceHandler
	public Function<ElementMessage<?, V, ?>, OutgoingConnectionMessage<T,V>> sourceHandler = message -> {
		if (message.hasSeen(getElement())) {
			return null;
		}
		V outgoingValue = outgoingValue(message.getValue());
		if (outgoingValue == null) {
			return null;
		}
		return new OutgoingConnectionMessage<T,V>(message, getElement(), outgoingValue, this);
	};
	
	@TargetHandler
	public Function<ElementMessage<?, V, ?>, IncomingConnectionMessage<T,V>> targetHandler = message -> {
		if (message.hasSeen(getElement())) {
			return null;
		}
		V incomingValue = incomingValue(message.getValue());
		if (incomingValue == null) {
			return null;
		}
		return new IncomingConnectionMessage<T,V>(message, getElement(), incomingValue, this);
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
		Collection<ElementMessage<?, V, ?>> messages = super.processMessage(message);
		if (sourceEndpoint != null && message instanceof IncomingConnectionMessage) {
			SourceMessage<T,?,V> sm = sourceEndpoint.apply((IncomingConnectionMessage<T, V>) message);
			if (sm != null) {
				messages.add(sm);
			}
		}
		
		if (targetEndpoint != null && message instanceof OutgoingConnectionMessage) {
			TargetMessage<T,?,V> tm = targetEndpoint.apply((OutgoingConnectionMessage<T, V>) message);
			if (tm != null) {
				messages.add(tm);
			}
		}
		
		return messages;
	}

}
