package org.nasdanika.drawio.processor;

import org.nasdanika.drawio.Connection;

/**
 * Mix-in interface for processor factories with no-operation endpoints.
 * @author Pavel
 *
 * @param <P> Processor type.
 * @param <H> Handler type.
 */
public interface NopEndpointProcessorFactory<P,H> extends ProcessorFactory<P,H,H> {

	/**
	 * Returns argument handler.
	 */
	@Override
	default H createEndpoint(Connection connection, H handler, HandlerType type) {
		return handler;
	}
	
}
