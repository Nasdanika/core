package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public interface NodeProcessorConfig<P,H,E,R> extends ProcessorConfig<P,R> {
	
	@Override
	Node getElement();

	Map<Connection, CompletionStage<E>> getIncomingEndpoints();
	
	Map<Connection, Consumer<H>> getIncomingHandlerConsumers();
	
	Map<Connection, CompletionStage<E>> getOutgoingEndpoints();
	
	Map<Connection, Consumer<H>> getOutgoingHandlerConsumers();
	
}
