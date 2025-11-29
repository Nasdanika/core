package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public class ConnectionProcessorConfigFilter<H,E,K> extends ProcessorConfigFilter<H,E,K,ConnectionProcessorConfig<H,E,K>> implements ConnectionProcessorConfig<H,E,K> {
	
	public ConnectionProcessorConfigFilter(ConnectionProcessorConfig<H,E,K> config) {
		super(config);
	}

	@Override
	public Connection getElement() {
		return config.getElement();
	}

	@Override
	public Synapse<H,E> getSourceSynapse() {
		return config.getSourceSynapse();
	}

	@Override
	public Synapse<H,E> getTargetSynapse() {
		return config.getTargetSynapse();
	}
	
}
