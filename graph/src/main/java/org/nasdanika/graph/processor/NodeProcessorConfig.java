package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public interface NodeProcessorConfig<H,E> extends ProcessorConfig<H,E> {
	
	@Override
	Node getElement();

	Map<Connection, Synapse<H,E>> getIncomingSynapses();
	
	Map<Connection, Synapse<H,E>> getOutgoingSynapses();
	
	default <P> NodeProcessorInfo<H,E,P> toInfo(P processor) {
		return new NodeProcessorInfo<H,E,P>(this, processor);
	}	
	
}
