package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;

public class ConnectionProcessorConfigAdapter<P, H, E> extends ProcessorConfigAdapter<P, ConnectionProcessorConfig<P, H, E>> implements ConnectionProcessorConfig<P, H, E> {

	public ConnectionProcessorConfigAdapter(ConnectionProcessorConfig<P, H, E> delegate) {
		super(delegate);
	}
	
	@Override
	public Connection getElement() {
		return delegate.getElement();
	}

	@Override
	public CompletionStage<E> getSourceEndpoint() {
		return delegate.getSourceEndpoint();
	}

	@Override
	public void setSourceHandler(H sourceHandler) {
		delegate.setSourceHandler(sourceHandler); 		
	}

	@Override
	public CompletionStage<E> getTargetEndpoint() {
		return delegate.getTargetEndpoint();
	}

	@Override
	public void setTargetHandler(H targetHandler) {
		delegate.setTargetHandler(targetHandler);
	}

}
