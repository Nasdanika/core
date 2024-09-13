package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public interface EndpointFactory<H, E> {
	
	E createEndpoint(Connection connection, H handler, HandlerType type);
	
	static <H> EndpointFactory<H,H> nopEndpointFactory() {
		return new EndpointFactory<H, H>() {

			@Override
			public H createEndpoint(Connection connection, H handler, HandlerType type) {
				return handler;
			}
			
		};
	}

}
