package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public class NodeProcessorConfigFilter<H,E,K> extends ProcessorConfigFilter<H,E,K,NodeProcessorConfig<H,E,K>> implements NodeProcessorConfig<H,E,K> {
	
	public NodeProcessorConfigFilter(NodeProcessorConfig<H,E,K> config) {
		super(config);
	}
		
	@Override
	public Node getElement() {
		return config.getElement();
	}

	@Override
	public Map<Connection, Synapse<H, E>> getIncomingSynapses() {
		return config.getIncomingSynapses();
	}

	@Override
	public Map<Connection, Synapse<H, E>> getOutgoingSynapses() {
		return config.getOutgoingSynapses();
	}
	
}
