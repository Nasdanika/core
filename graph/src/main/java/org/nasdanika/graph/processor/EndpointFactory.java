package org.nasdanika.graph.processor;

import org.nasdanika.graph.Element;

public interface EndpointFactory<H, E> {
	
	E createEndpoint(Element element, H handler, HandlerType type);
	
	static <H> EndpointFactory<H,H> nopEndpointFactory() {
		return new EndpointFactory<H, H>() {

			@Override
			public H createEndpoint(Element element, H handler, HandlerType type) {
				return handler;
			}
			
		};
	}

}
