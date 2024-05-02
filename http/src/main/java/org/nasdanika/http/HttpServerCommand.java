package org.nasdanika.http;

import org.nasdanika.cli.CommandBase;

import picocli.CommandLine.Command;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

@Command(
		description = "Serves HTTP routes",
		name = "http-server")
public class HttpServerCommand extends CommandBase {
	
	
	@Override
	public Integer call() throws Exception {
		// Starter code
		// TODO - routes, mix-in
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
		
		
		return 0;
	}

}
