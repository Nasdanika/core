package org.nasdanika.drawio._processors;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Node;

/**
 * Node processor which provides access to endpoint and handler consumer to the client code.  
 * @author Pavel
 *
 * @param <T>
 */
public interface ExportNodeProcessor<T,U,R,V,S> extends Resolver<T> {
			
	Node getNode();
	
	T getParentProcessor();
	
	Map<Element, T> getChildProcessors();
	
	Map<Connection, Function<U,R>> getInboundEndpoints();
	
	Map<Connection, Consumer<Function<V,S>>> getInboundHandlerConsumers();
	
	Map<Connection, Function<U,R>> getOutboundEndpoints();
	
	Map<Connection, Consumer<Function<V,S>>> getOutboundHandlerConsumers();
	
	
}
