package org.nasdanika.graph.processor.emf;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;

public class ConnectionProcessorConfigAdapter<P, H, E, R> extends ProcessorConfigAdapter<P, R, ConnectionProcessorConfig<P, H, E, R>> implements ConnectionProcessorConfig<P, H, E, R> {

	public ConnectionProcessorConfigAdapter(ConnectionProcessorConfig<P, H, E, R> delegate) {
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
