package org.nasdanika.graph.processor.emf;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;

public class ConnectionProcessorConfigAdapter<H, E> extends ProcessorConfigAdapter<ConnectionProcessorConfig<H, E>> implements ConnectionProcessorConfig<H, E> {

	public ConnectionProcessorConfigAdapter(ConnectionProcessorConfig<H, E> delegate) {
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
