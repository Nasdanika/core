package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public class NodeProcessorConfigAdapter<P, H, E> extends ProcessorConfigAdapter<P, NodeProcessorConfig<P, H, E>> implements NodeProcessorConfig<P, H, E> {

	public NodeProcessorConfigAdapter(NodeProcessorConfig<P, H, E> delegate) {
		super(delegate);
	}

	@Override
	public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
		return delegate.getIncomingEndpoints();
	}

	@Override
	public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
		return delegate.getIncomingHandlerConsumers();
	}

	@Override
	public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
		return delegate.getOutgoingEndpoints();
	}

	@Override
	public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
		return delegate.getOutgoingHandlerConsumers();
	}
	
	@Override
	public Node getElement() {
		return delegate.getElement();
	}

}
