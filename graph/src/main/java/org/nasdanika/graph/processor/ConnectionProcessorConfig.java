package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Connection;

public interface ConnectionProcessorConfig<H,E> extends ProcessorConfig<H,E> {
	
	@Override
	Connection getElement();

	CompletionStage<E> getSourceEndpoint();
	
	void setSourceHandler(H sourceHandler);
	
	CompletionStage<E> getTargetEndpoint();
	
	void setTargetHandler(H targetHandler);
	
	default <P> ConnectionProcessorInfo<H,E,P> toInfo(P processor) {
		return new ConnectionProcessorInfo<H,E,P>(this, processor);
	}	
	
}
