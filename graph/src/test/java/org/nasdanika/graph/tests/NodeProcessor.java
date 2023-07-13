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

/**
 * Passes this element as endpoint argument and expects the other side element back.
 */
public class NodeProcessor implements Supplier<Integer> {
	
	private NodeProcessorConfig<Function<Element, Element>, Function<Element, Element>> config;
	private Map<Connection, Function<Element, Element>> incomingEndpoints = new ConcurrentHashMap<>();
	private Map<Connection, Function<Element, Element>> outgoingEndpoints = new ConcurrentHashMap<>();
	private boolean passThrough;

	public NodeProcessor(NodeProcessorConfig<Function<Element,Element>, Function<Element,Element>> config, Consumer<CompletionStage<?>> stageConsumer, boolean passThrough) {
		this.config = config;
		this.passThrough = passThrough;
		for (Entry<Connection, Consumer<Function<Element, Element>>> ihc: config.getIncomingHandlerConsumers().entrySet()) {
			ihc.getValue().accept(e -> {
				assertEquals(passThrough ? ihc.getKey().getSource() : ihc.getKey() , e);
				return config.getElement();
			});			
		}
		
		for (Entry<Connection, Consumer<Function<Element, Element>>> ohc: config.getOutgoingHandlerConsumers().entrySet()) {
			ohc.getValue().accept(e -> {
				assertEquals(passThrough ? ohc.getKey().getTarget() : ohc.getKey() , e);
				return config.getElement();
			});			
		}
		
		for (Entry<Connection, CompletionStage<Function<Element, Element>>> ie: config.getIncomingEndpoints().entrySet()) {
			stageConsumer.accept(ie.getValue().thenAccept(e -> incomingEndpoints.put(ie.getKey(), e)));
		}
		
		for (Entry<Connection, CompletionStage<Function<Element, Element>>> oe: config.getOutgoingEndpoints().entrySet()) {
			stageConsumer.accept(oe.getValue().thenAccept(e -> outgoingEndpoints.put(oe.getKey(), e)));
		}
		
	}
	
	@Override
	public Integer get() {
		int counter = 0; 
		assertEquals(incomingEndpoints.size(), config.getIncomingEndpoints().size());
		assertEquals(outgoingEndpoints.size(), config.getOutgoingEndpoints().size());
		
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
