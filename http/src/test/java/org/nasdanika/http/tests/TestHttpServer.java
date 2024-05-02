package org.nasdanika.http.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class TestHttpServer {
	
	@Disabled
	@Test
	public void testHttpServer() {
		DisposableServer server =
				HttpServer.create()
					.port(8080)
				          .route(routes ->
				              routes.get("/hello",        
				                        (request, response) -> response.sendString(Mono.just("Hello World!")))
				                    .post("/echo",        
				                        (request, response) -> response.send(request.receive().retain()))
				                    .get("/path/{param}", 
				                        (request, response) -> response.sendString(Mono.just(request.param("param"))))
				                    .ws("/ws",            
				                        (wsInbound, wsOutbound) -> wsOutbound.send(wsInbound.receive().retain())))
				          .bindNow();

		server.onDispose().block();		
	}

}
