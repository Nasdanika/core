package org.nasdanika.drawio._processors;

import java.util.function.Function;

/**
 * Processor factory which returns the argument handler from createEndpoint.
 * @author Pavel
 *
 * @param <T>
 * @param <U>
 * @param <R>
 */
public class NopEndpointProcessorFactory<T,U,R> extends ProcessorFactory<T, U, R, U, R> {
	
	@Override
	protected Function<U, R> createEndpoint(Function<U, R> handler) {
		return handler;
	}

}
