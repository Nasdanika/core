package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;

public interface ConnectionProcessorConfig<H,E> extends ProcessorConfig {
	
	@Override
	Connection getElement();

	CompletionStage<E> getSourceEndpoint();
	
	void setSourceHandler(H sourceHandler);
	
	CompletionStage<E> getTargetEndpoint();
	
	void setTargetHandler(H targetHandler);
	
}
