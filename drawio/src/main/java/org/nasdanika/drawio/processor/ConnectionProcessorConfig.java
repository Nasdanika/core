package org.nasdanika.drawio.processor;

import java.util.function.Function;

import org.nasdanika.drawio.Connection;

public interface ConnectionProcessorConfig<P,T,R,U,S> extends ElementProcessorConfig<Connection, P> {

	Function<T,R> getSourceEndpoint();
	
	void setSourceHandler(Function<U,S> sourceHandler);
	
	Function<T,R> getTargetEndpoint();
	
	void setTargetHandler(Function<U,S> targetHandler);
	
}
