package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

public class SynapseFilter<H,E> implements Synapse<H,E> {
	
	private Synapse<H, E> target;

	public SynapseFilter(Synapse<H,E> target) {
		this.target = target;
	}

	@Override
	public CompletionStage<E> getEndpoint() {
		return target.getEndpoint();
	}

	@Override
	public boolean setHandler(H handler) {
		return target.setHandler(handler);		
	}

}
