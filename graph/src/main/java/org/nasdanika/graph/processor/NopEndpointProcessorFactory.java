package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

/**
 * Mix-in interface for processor factories with no-operation endpoints.
 * @author Pavel
 *
 * @param <P> Processor type.
 * @param <H> Handler type.
 */
public interface NopEndpointProcessorFactory<P,H,R> extends ProcessorFactory<P,H,H,R> {

	/**
	 * Returns argument handler.
	 */
	@Override
	default H createEndpoint(Connection connection, H handler, HandlerType type) {
		return handler;
	}
	
}
