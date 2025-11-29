package org.nasdanika.graph.processor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.graph.Connection;

class ConnectionProcessorConfigImpl<H,E,K> extends ProcessorConfigImpl<H,E,K> implements ConnectionProcessorConfig<H,E,K> {

	private CompletableFuture<E> sourceEndpoint = new CompletableFuture<>();
	private CompletableFuture<E> targetEndpoint = new CompletableFuture<>();
	
	private CompletableFuture<H> sourceHandlerConsumer = new CompletableFuture<H>();	
	private CompletableFuture<H> targetHandlerConsumer = new CompletableFuture<H>();
	
	ConnectionProcessorConfigImpl(Connection element, Function<H,E> clientEndpointFactory, Function<H,E> processorEndpointFactory) {
		super(element, clientEndpointFactory, processorEndpointFactory);
	}
	
	@Override
	public Connection getElement() {
		return (Connection) super.getElement();
	}
	
	@Override
	public Synapse<H,E> getSourceSynapse() {
		return new Synapse<H,E>() {

			@Override
			public CompletionStage<E> getEndpoint() {
				return sourceEndpoint;
			}

			@Override
			public boolean setHandler(H handler) {
				return sourceHandlerConsumer.complete(handler);
			}
		};
	}
	
	@Override
	public Synapse<H,E> getTargetSynapse() {
		return new Synapse<H,E>() {

			@Override
			public CompletionStage<E> getEndpoint() {
				return targetEndpoint;
			}

			@Override
			public boolean setHandler(H handler) {
				return targetHandlerConsumer.complete(handler);
			}
		};
	}
	
	// --- Wiring methods ---
		
	/**
	 * Source handler endpoint is source's outgoing endpoint for this connection  
	 */
	void wireSourceHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		sourceHandlerConsumer.thenApply(endpointFactory).thenAccept(endpointConsumer);		
	}
	
	/**
	 * Target handler endpoint is target's incoming endpoint for this connection  
	 */
	void wireTargetHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		targetHandlerConsumer.thenApply(endpointFactory).thenAccept(endpointConsumer);		
	}
	
	void setSourceEndpoint(E se) {
		sourceEndpoint.complete(se);
	}
	
	void setTargetEndpoint(E te) {
		targetEndpoint.complete(te);
	}

}
