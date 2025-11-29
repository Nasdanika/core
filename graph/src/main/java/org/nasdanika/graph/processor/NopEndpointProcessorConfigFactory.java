package org.nasdanika.graph.processor;

import org.nasdanika.graph.Element;

/**
 * Mix-in interface for processor factories with no-operation endpoints.
 * @author Pavel
 *
 * @param <P> Processor type.
 * @param <H> Handler type.
 */
public class NopEndpointProcessorConfigFactory<H,K> extends ProcessorConfigFactory<H,H,K> {

	/**
	 * Returns argument handler.
	 */
	@Override
	public H createEndpoint(Element element, H handler, HandlerType type) {
		return handler;
	}
	
}
