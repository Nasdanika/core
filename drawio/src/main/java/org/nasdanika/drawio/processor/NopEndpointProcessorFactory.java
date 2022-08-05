package org.nasdanika.drawio.processor;

import java.util.function.Function;

import org.nasdanika.drawio.Connection;

public interface NopEndpointProcessorFactory<P,T,R> extends ProcessorFactory<P,T,R,T,R> {

	@Override
	default Function<T, R> createEndpoint(Connection connection, Function<T, R> handler, EndpointType type) {
		return handler;
	}
	
}
