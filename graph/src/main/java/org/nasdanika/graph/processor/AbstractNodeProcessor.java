package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * 
 * @param <T> Element type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public abstract class AbstractNodeProcessor<T extends Node,H,E> extends AbstractElementProcessor<T,H,E> {
	
	@OutgoingHandler
	public final H getOutgoingHandler(Connection outgoingConnection) {
		return getOutgoingHandler(
				outgoingConnection,
				this::getParentEndpoint,
				childEndpoints,
				incomingEndpoints,
				outgoingEndpoints);		
	}
		
	protected abstract H getOutgoingHandler(
			Connection outgoingConnection, 
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);		
	
	@IncomingHandler
	public final H getIncomingHandler(Connection incomingConnection) {
		return getIncomingHandler(
				incomingConnection,
				this::getParentEndpoint,
				childEndpoints,
				incomingEndpoints,
				outgoingEndpoints);		
	}
	
	protected abstract H getIncomingHandler(
			Connection incomingConnection, 
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints, 
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
	protected final H getHandler(E parentEndpoint, Map<Element, E> childEndpoints) {
		return getHandler(parentEndpoint, childEndpoints, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract H getHandler(
			E parentEndpoint, 
			Map<Element,E> childEndpoints, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);
	
	@Override
	protected final E getChildEndpoint(Element child, Supplier<E> parentEndpointSupplier, Map<Element, E> childEndpoints) {
		return getChildEndpoint(child, parentEndpointSupplier, childEndpoints, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract E getChildEndpoint(
			Element child, 
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);
	
	@Override
	protected final E getParentEndpoint(E parentEndpoint, Map<Element, E> childEndpoints) {
		return getParentEndpoint(parentEndpoint, childEndpoints, incomingEndpoints, outgoingEndpoints);
	}
	
	protected abstract E getParentEndpoint(
			E parentEndpoint,
			Map<Element,E> childEndpoints, 
			Map<Connection,E> incomingEndpoints, 
			Map<Connection,E> outgoingEndpoints);	

}
