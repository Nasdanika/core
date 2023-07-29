package org.nasdanika.graph.processor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.graph.Connection;

class ConnectionProcessorConfigImpl<H,E> extends ProcessorConfigImpl implements ConnectionProcessorConfig<H, E> {

	private CompletableFuture<E> sourceEndpoint = new CompletableFuture<>();
	private CompletableFuture<E> targetEndpoint = new CompletableFuture<>();
	
	private Consumer<H> sourceHandlerConsumer;	
	private Consumer<H> targetHandlerConsumer;
	

	ConnectionProcessorConfigImpl(Connection element) {
		super(element);
	}
	
	@Override
	public Connection getElement() {
		return (Connection) super.getElement();
	}

	@Override
	public CompletionStage<E> getSourceEndpoint() {
		return sourceEndpoint;
	}

	@Override
	public void setSourceHandler(H sourceHandler) {
		sourceHandlerConsumer.accept(sourceHandler);		
	}

	@Override
	public CompletionStage<E> getTargetEndpoint() {
		return targetEndpoint;
	}

	@Override
	public void setTargetHandler(H targetHandler) {
		targetHandlerConsumer.accept(targetHandler);
	}
	
	// --- Wiring methods ---
		
	/**
	 * Source handler endpoint is source's outgoing endpoint for this connection  
	 */
	void wireSourceHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		sourceHandlerConsumer =	handler ->  endpointConsumer.accept(endpointFactory.apply(handler));		
	}
	
	/**
	 * Target handler endpoint is target's incoming endpoint for this connection  
	 */
	void wireTargetHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		targetHandlerConsumer = handler ->  endpointConsumer.accept(endpointFactory.apply(handler));		
	}
	
	void setSourceEndpoint(E se) {
		sourceEndpoint.complete(se);
	}
	
	void setTargetEndpoint(E te) {
		targetEndpoint.complete(te);
	}

}
