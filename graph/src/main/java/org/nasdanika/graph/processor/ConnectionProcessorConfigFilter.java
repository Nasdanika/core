package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public class ConnectionProcessorConfigFilter<H,E> extends ProcessorConfigFilter<H,E,ConnectionProcessorConfig<H,E>> implements ConnectionProcessorConfig<H,E> {
	
	public ConnectionProcessorConfigFilter(ConnectionProcessorConfig<H, E> config) {
		super(config);
	}

	@Override
	public Connection getElement() {
		return config.getElement();
	}

	@Override
	public Synapse<H, E> getSourceSynapse() {
		return config.getSourceSynapse();
	}

	@Override
	public Synapse<H, E> getTargetSynapse() {
		return config.getTargetSynapse();
	}
	
}
