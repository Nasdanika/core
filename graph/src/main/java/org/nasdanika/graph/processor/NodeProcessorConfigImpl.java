package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

class NodeProcessorConfigImpl<H,E,K> extends ProcessorConfigImpl<H,E,K> implements NodeProcessorConfig<H,E,K> {

	// Endpoints are created for each connection and completed when a handler is passes to a corresponding handler consumer.
	private Map<Connection, CompletableFuture<E>> incomingEndpoints;
	private Map<Connection, CompletableFuture<E>> outgoingEndpoints;
	
	// Handler consumers are added when a corresponding endpoint becomes available
	private Map<Connection, CompletableFuture<H>> incomingHandlerConsumers = new ConcurrentHashMap<>();	
	private Map<Connection, CompletableFuture<H>> outgoingHandlerConsumers = new ConcurrentHashMap<>();

	NodeProcessorConfigImpl(
			Node node, 
			Function<H,E> clientEndpointFactory, 
			Function<H,E> processorEndpointFactory) {
		super(node, clientEndpointFactory, processorEndpointFactory);
		
		incomingEndpoints = node.getIncomingConnections().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), c -> new CompletableFuture<E>()));
		incomingHandlerConsumers = node.getIncomingConnections().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), c -> new CompletableFuture<H>()));
		
		outgoingEndpoints = node.getOutgoingConnections().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), c -> new CompletableFuture<E>()));
		outgoingHandlerConsumers = node.getOutgoingConnections().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), c -> new CompletableFuture<H>()));
	}
	
	// --- Client API ---
	
	@Override
	public Node getElement() {
		return (Node) super.getElement();
	}
	
	@Override
	public Map<Connection, Synapse<H, E>> getIncomingSynapses() {
		return incomingEndpoints
				.entrySet()
				.stream()
				.collect(Collectors.toUnmodifiableMap(
						Map.Entry::getKey, 
						e -> new Synapse<H,E>() {

							@Override
							public CompletionStage<E> getEndpoint() {
								return e.getValue();
							}

							@Override
							public boolean setHandler(H handler) {
								return incomingHandlerConsumers.get(e.getKey()).complete(handler);
							}
							
						}));
	}
	
	@Override
	public Map<Connection, Synapse<H, E>> getOutgoingSynapses() {
		return outgoingEndpoints
				.entrySet()
				.stream()
				.collect(Collectors.toUnmodifiableMap(
						Map.Entry::getKey, 
						e -> new Synapse<H,E>() {

							@Override
							public CompletionStage<E> getEndpoint() {
								return e.getValue();
							}

							@Override
							public boolean setHandler(H handler) {
								return outgoingHandlerConsumers.get(e.getKey()).complete(handler);
							}
							
						}));
	}
	
	// --- Wiring methods ---
	
	/**
	 * Incoming handler endpoint is either incoming connection's target endppoint or the other end's outgoing endpoint if pass-through  
	 * @param incomingConnection
	 */
	void wireIncomingHandlerEndpoint(Connection incomingConnection, Function<H,E> incomingEndpointFactory, Consumer<E> endpointConsumer) {		
		incomingHandlerConsumers.get(incomingConnection).thenApply(incomingEndpointFactory).thenAccept(endpointConsumer);
	}
	
	/**
	 * Outgoing handler endpoint is either outgoing connection's source endppoint or the other end's incoming endpoint if pass-through  
	 * @param outgoingConnection
	 */
	void wireOutgoingHandlerEndpoint(Connection outgoingConnection, Function<H,E> outgoingEndpointFactory, Consumer<E> endpointConsumer) {		
		outgoingHandlerConsumers.get(outgoingConnection).thenApply(outgoingEndpointFactory).thenAccept(endpointConsumer); 
	}
		
	void setIncomingEndpoint(Connection connection, E ie) {		
		incomingEndpoints.get(connection).complete(ie);
	}
		
	void setOutgoingEndpoint(Connection connection, E oe) {		
		outgoingEndpoints.get(connection).complete(oe);
	}

}
