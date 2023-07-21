package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;

public class ConnectionProcessorInfo<P, H, E> extends ProcessorInfo<P> implements ConnectionProcessorConfig<H, E> {
	
	public ConnectionProcessorInfo(ConnectionProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Connection getElement() {
		return ((ConnectionProcessorConfig<H, E>) config).getElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<E> getSourceEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getSourceEndpoint();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSourceHandler(H sourceHandler) {
		((ConnectionProcessorConfig<H, E>) config).setSourceHandler(sourceHandler);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<E> getTargetEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getTargetEndpoint();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setTargetHandler(H targetHandler) {		
		((ConnectionProcessorConfig<H, E>) config).setTargetHandler(targetHandler);
	}

}
