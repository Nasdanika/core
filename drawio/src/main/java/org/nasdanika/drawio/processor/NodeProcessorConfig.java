package org.nasdanika.drawio.processor;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

public interface NodeProcessorConfig<P,T,R,U,S> extends ElementProcessorConfig<Node, P> {

	Map<Connection, Function<T,R>> getInboundEndpoints();
	
	Map<Connection, Consumer<Function<U,S>>> getInboundHandlerConsumers();
	
	Map<Connection, Function<T,R>> getOutboundEndpoints();
	
	Map<Connection, Consumer<Function<U,S>>> getOutboundHandlerConsumers();
	
}
