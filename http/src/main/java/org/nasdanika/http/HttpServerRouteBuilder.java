package org.nasdanika.http;

import reactor.netty.http.server.HttpServerRoutes;

/**
 * Service interface for building routes.
 */
public interface HttpServerRouteBuilder {
	
	void buildRoutes(HttpServerRoutes routes); // TODO - progress monitor?

}
