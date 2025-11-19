package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;

public class ConnectionProcessorConfigFilter<H,E> extends ProcessorConfigFilter<H,E,ConnectionProcessorConfig<H,E>> implements ConnectionProcessorConfig<H,E> {
	
	public ConnectionProcessorConfigFilter(ConnectionProcessorConfig<H, E> config) {
		super(config);
	}

	@Override
	public Connection getElement() {
		return config.getElement();
	}

	public CompletionStage<E> getSourceEndpoint() {
		return config.getSourceEndpoint();
	}
	
	public void setSourceHandler(H sourceHandler) {
		config.setSourceHandler(sourceHandler);
	}
	
	public CompletionStage<E> getTargetEndpoint() {
		return config.getTargetEndpoint();
	}
	
	public void setTargetHandler(H targetHandler) {
		config.setTargetHandler(targetHandler);
	}
	
}
