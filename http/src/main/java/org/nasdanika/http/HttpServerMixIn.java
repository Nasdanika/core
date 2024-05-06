package org.nasdanika.http;

import picocli.CommandLine.Option;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRoutes;

/**
 * Mix-in or base class for commands which serve HTTP requests
 */
public class HttpServerMixIn {
		
	@Option(
			names = "--http-host", 
			description = "HTTP host (network interface) to bind to")
	private String httpHost;
	
	@Option(
			names = "--http-port", 
			description = {
				"HTTP port. If a port is not specified,",
				"an ephemeral port is used" })
	private Integer httpPort;
	
	public String getHttpHost() {
		return httpHost;
	}
	
	public Integer getHttpPort() {
		return httpPort;
	}
	
	protected HttpServer createServer() {
		HttpServer server = HttpServer.create();
		if (httpPort != null) {
			server.port(httpPort);
		}
		if (httpHost != null) {
			server.host(httpHost);
		}
		server.route(this::buildRoutes);
		
		return server; // .bindNow(); .onDispose().block();
	}
	
	protected void buildRoutes(HttpServerRoutes routes) {
		
	}

}
