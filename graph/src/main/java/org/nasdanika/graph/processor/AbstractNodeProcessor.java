package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * 
 * @param <T> Element type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public abstract class AbstractNodeProcessor<T extends Node,H,E> extends AbstractElementProcessor<T,H> {
	
	@OutgoingHandler
	public final H getOutgoingHandler(Connection outgoingConnection) {
		return getOutgoingHandler(
				outgoingConnection,
				parentProcessor == null ? null : parentProcessor.getChildHandler(getElement()),
				this::createChildHandler,
				incomingEndpoints,
				outgoingEndpoints);		
	}
		
	protected abstract H getOutgoingHandler(
			Connection outgoingConnection, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);		
	
	@IncomingHandler
	public final H getIncomingHandler(Connection incomingConnection) {
		return getIncomingHandler(
				incomingConnection,
				parentProcessor == null ? null : parentProcessor.getChildHandler(getElement()),
				this::createChildHandler,
				incomingEndpoints,
				outgoingEndpoints);		
	}
	
	protected abstract H getIncomingHandler(
			Connection incomingConnection, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);		
		
	protected Map<Connection,E> outgoingEndpoints = new ConcurrentHashMap<>();
		
	@OutgoingEndpoint
	public void addOutgoingEndpoints(Connection connection, E outgoingEndpoint) {
		if (outgoingEndpoint != null) {
			outgoingEndpoints.put(connection, outgoingEndpoint);
		}
	}
	
	protected Map<Connection,E> incomingEndpoints = new ConcurrentHashMap<>();
		
	@IncomingEndpoint
	public void addIncomingEndpoints(Connection connection, E incomingEndpoint) {
		if (incomingEndpoint != null) {
			incomingEndpoints.put(connection, incomingEndpoint);
		}
	}
		
	@Override
	protected final H getHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
		return getHandler(parentHandler, childHandlerProvider, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract H getHandler(
			H parentHandler, 
			Function<Element,H> childHandlerProvider, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);	
	
	@Override
	protected final H getChildHandler(Element child, H parentHandler, Function<Element,H> childHandlerProvider) {
		return getChildHandler(child, parentHandler, childHandlerProvider, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract H getChildHandler(
			Element child, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);	
	
	@Override
	protected final H getParentHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
		return getParentHandler(parentHandler, childHandlerProvider, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract H getParentHandler(
			H parentHandler,
			Function<Element,H> childHandlerProvider, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);	

}
