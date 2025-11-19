package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public interface NodeProcessorConfig<H,E> extends ProcessorConfig<H,E> {
	
	@Override
	Node getElement();

	Map<Connection, CompletionStage<E>> getIncomingEndpoints();
	
	Map<Connection, Consumer<H>> getIncomingHandlerConsumers();
	
	Map<Connection, CompletionStage<E>> getOutgoingEndpoints();
	
	Map<Connection, Consumer<H>> getOutgoingHandlerConsumers();
	
	default <P> NodeProcessorInfo<H,E,P> toInfo(P processor) {
		return new NodeProcessorInfo<H,E,P>(this, processor);
	}	
	
}
