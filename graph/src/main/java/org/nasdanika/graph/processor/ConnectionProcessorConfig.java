package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public interface ConnectionProcessorConfig<H,E> extends ProcessorConfig<H,E> {
	
	@Override
	Connection getElement();
	
	Synapse<H,E> getSourceSynapse();
	
	Synapse<H,E> getTargetSynapse();
	
	default <P> ConnectionProcessorInfo<H,E,P> toInfo(P processor) {
		return new ConnectionProcessorInfo<H,E,P>(this, processor);
	}	
	
}
