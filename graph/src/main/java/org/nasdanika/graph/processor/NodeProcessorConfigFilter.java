package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public class NodeProcessorConfigFilter<H,E> extends ProcessorConfigFilter<H,E,NodeProcessorConfig<H,E>> implements NodeProcessorConfig<H,E> {
	
	public NodeProcessorConfigFilter(NodeProcessorConfig<H,E> config) {
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
