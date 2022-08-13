package org.nasdanika.drawio.processor;

import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

public interface NodeProcessorConfig<P,H,E> extends ElementProcessorConfig<P> {
	
	@Override
	Node getElement();

	Map<Connection, E> getInboundEndpoints();
	
	Map<Connection, Consumer<H>> getInboundHandlerConsumers();
	
	Map<Connection, E> getOutboundEndpoints();
	
	Map<Connection, Consumer<H>> getOutboundHandlerConsumers();
	
}
