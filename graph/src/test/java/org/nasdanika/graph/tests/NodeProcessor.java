package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Synapse;

/**
 * Passes this element as endpoint argument and expects the other side element back.
 */
public class NodeProcessor implements Supplier<Integer> {
	
	private NodeProcessorConfig<Function<Element, Element>, Function<Element, Element>, String> config;
	private Map<Connection, Function<Element, Element>> incomingEndpoints = new ConcurrentHashMap<>();
	private Map<Connection, Function<Element, Element>> outgoingEndpoints = new ConcurrentHashMap<>();
	private boolean passThrough;

	public NodeProcessor(
			NodeProcessorConfig<Function<Element,Element>, Function<Element,Element>, String> config, 
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			boolean passThrough) {
		this.config = config;
		this.passThrough = passThrough;
		for (Entry<Connection, Synapse<Function<Element, Element>, Function<Element, Element>>> ise: config.getIncomingSynapses().entrySet()) {
			ise.getValue().setHandler(e -> {
				assertEquals(passThrough ? ise.getKey().getSource() : ise.getKey() , e);
				return config.getElement();
			});			
		}
		
		for (Entry<Connection, Synapse<Function<Element, Element>, Function<Element, Element>>> ose: config.getOutgoingSynapses().entrySet()) {
			ose.getValue().setHandler(e -> {
				assertEquals(passThrough ? ose.getKey().getTarget() : ose.getKey() , e);
				return config.getElement();
			});			
		}
		
		for (Entry<Connection, Synapse<Function<Element, Element>, Function<Element, Element>>> ise: config.getIncomingSynapses().entrySet()) {
			endpointWiringStageConsumer.accept(ise.getValue().getEndpoint().thenAccept(e -> incomingEndpoints.put(ise.getKey(), e)));
		}
		
		for (Entry<Connection, Synapse<Function<Element, Element>, Function<Element, Element>>> ose: config.getOutgoingSynapses().entrySet()) {
			endpointWiringStageConsumer.accept(ose.getValue().getEndpoint().thenAccept(e -> outgoingEndpoints.put(ose.getKey(), e)));
		}
		
	}
	
	@Override
	public Integer get() {
		int counter = 0; 
		assertEquals(config.getIncomingSynapses().size(), incomingEndpoints.size());
		assertEquals(config.getOutgoingSynapses().size(), outgoingEndpoints.size());
		
		for (Entry<Connection, Function<Element, Element>> ie: incomingEndpoints.entrySet()) {			
			Element ret = ie.getValue().apply(config.getElement());			
			assertEquals(passThrough ? ie.getKey().getSource() : ie.getKey(), ret);
			++counter;
		}		
		
		for (Entry<Connection, Function<Element, Element>> oe: outgoingEndpoints.entrySet()) {			
			Element ret = oe.getValue().apply(config.getElement());			
			assertEquals(passThrough ? oe.getKey().getTarget() : oe.getKey() , ret);
			++counter;
		}		
		
		return counter;
	}

}
