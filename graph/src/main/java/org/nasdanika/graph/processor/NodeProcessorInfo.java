package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public class NodeProcessorInfo<P, H, E> extends ProcessorInfo<P> implements NodeProcessorConfig<H, E> {
	
	public NodeProcessorInfo(NodeProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Node getElement() {
		return ((NodeProcessorConfig<H, E>) config).getElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
		return ((NodeProcessorConfig<H, E>) config).getIncomingEndpoints();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
		return ((NodeProcessorConfig<H, E>) config).getIncomingHandlerConsumers();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
		return ((NodeProcessorConfig<H, E>) config).getOutgoingEndpoints();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
		return ((NodeProcessorConfig<H, E>) config).getOutgoingHandlerConsumers();
	}

}
