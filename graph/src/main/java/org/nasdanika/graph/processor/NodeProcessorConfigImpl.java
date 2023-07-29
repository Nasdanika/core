package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

class NodeProcessorConfigImpl<H,E> extends ProcessorConfigImpl implements NodeProcessorConfig<H, E> {

	// Endpoints are created for each connection and completed when a handler is passes to a corresponding handler consumer.
	private Map<Connection, CompletableFuture<E>> incomingEndpoints;
	private Map<Connection, CompletableFuture<E>> outgoingEndpoints;
	
	// Handler consumers are added when a corresponding endpoint becomes available
	private Map<Connection, Consumer<H>> incomingHandlerConsumers = new ConcurrentHashMap<>();	
	private Map<Connection, Consumer<H>> outgoingHandlerConsumers = new ConcurrentHashMap<>();

	NodeProcessorConfigImpl(Node node) {
		super(node);
		incomingEndpoints = node.getIncomingConnections().stream().collect(Collectors.toUnmodifiableMap(c -> c, c -> new CompletableFuture<E>()));
		outgoingEndpoints = node.getOutgoingConnections().stream().collect(Collectors.toUnmodifiableMap(c -> c, c -> new CompletableFuture<E>()));
	}
	
	// --- Client API ---
	
	@Override
	public Node getElement() {
		return (Node) super.getElement();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
		return (Map) incomingEndpoints; // Rude cast to make the compile happy
	}

	@Override
	public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
		return Collections.unmodifiableMap(incomingHandlerConsumers);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
		return (Map) outgoingEndpoints;// Rude cast to make the compile happy
	}

	@Override
	public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
		return Collections.unmodifiableMap(outgoingHandlerConsumers);
	}
	
	// --- Wiring methods ---
	
	/**
	 * Incoming handler endpoint is either incoming connection's target endppoint or the other end's outgoing endpoint if pass-through  
	 * @param incomingConnection
	 */
	void wireIncomingHandlerEndpoint(
			Connection incomingConnection, 
			Function<H,E> endpointFactory,
			Consumer<E> endpointConsumer) {
		
		incomingHandlerConsumers.put(
				incomingConnection, 
				handler ->  endpointConsumer.accept(endpointFactory.apply(handler)));		
	}
	
	/**
	 * Outgoing handler endpoint is either outgoing connection's source endppoint or the other end's incoming endpoint if pass-through  
	 * @param outgoingConnection
	 */
	void wireOutgoingHandlerEndpoint(
			Connection outgoingConnection, 
			Function<H,E> endpointFactory,
			Consumer<E> endpointConsumer) {
		
		outgoingHandlerConsumers.put(
				outgoingConnection, 
				handler ->  endpointConsumer.accept(endpointFactory.apply(handler)));		
	}
		
	void setIncomingEndpoint(Connection connection, E ie) {		
		incomingEndpoints.get(connection).complete(ie);
	}
		
	void setOutgoingEndpoint(Connection connection, E oe) {		
		outgoingEndpoints.get(connection).complete(oe);
	}

}
