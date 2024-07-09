package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public class NodeProcessorConfigFilter<H,E> extends ProcessorConfigFilter<NodeProcessorConfig<H,E>> implements NodeProcessorConfig<H,E> {
	
	public NodeProcessorConfigFilter(NodeProcessorConfig<H,E> config) {
		super(config);
	}
		
	@Override
	public Node getElement() {
		return config.getElement();
	}

	public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
		return config.getIncomingEndpoints();
	}
	
	public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
		return config.getIncomingHandlerConsumers();
	}
	
	public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
		return config.getOutgoingEndpoints();
	}
	
	public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
		return config.getOutgoingHandlerConsumers();
	}
	
}
