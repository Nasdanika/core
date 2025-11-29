package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public interface ConnectionProcessorConfig<H,E,K> extends ProcessorConfig<H,E,K> {
	
	@Override
	Connection getElement();
	
	Synapse<H,E> getSourceSynapse();
	
	Synapse<H,E> getTargetSynapse();
	
	default <P> ConnectionProcessorInfo<H,E,K,P> toInfo(P processor) {
		return new ConnectionProcessorInfo<H,E,K,P>(this, processor);
	}	
	
}
