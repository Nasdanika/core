package org.nasdanika.graph.processor.emf;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.NodeProcessorConfig;

public class NodeProcessorConfigAdapter<H, E> extends ProcessorConfigAdapter<NodeProcessorConfig<H, E>> implements NodeProcessorConfig<H, E> {

	public NodeProcessorConfigAdapter(NodeProcessorConfig<H, E> delegate) {
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
