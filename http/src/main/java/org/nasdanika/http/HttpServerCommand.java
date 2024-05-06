package org.nasdanika.http;

import java.time.Duration;
import java.util.Collection;

import org.nasdanika.cli.CommandBase;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRoutes;

@Command(
		description = "Serves HTTP routes",
		name = "http-server")
public class HttpServerCommand extends CommandBase {
	
	private Collection<HttpServerRouteBuilder> routeBuilders;

	public HttpServerCommand(Collection<HttpServerRouteBuilder> routeBuilders) {
		this.routeBuilders = routeBuilders;
	}
	
	@Mixin
	private HttpServerMixIn serverMixIn;	
	
	@Option(
			names = "--http-server-shutdown-timeout", 
			description = {
				"Timeout in seconds, ",
				"defaults to ${DEFAULT-VALUE} seconds"
			},
			defaultValue = "3")
	private int timeout;

	protected void buildRoutes(HttpServerRoutes routes) {
		if (routeBuilders != null) {
			for (HttpServerRouteBuilder routeBuilder: routeBuilders) {
				routeBuilder.buildRoutes(routes);
			}
		}
	}
	
	@Override
	public Integer call() throws Exception {
		HttpServer httpServer = serverMixIn.createServer();
		httpServer.route(this::buildRoutes);
		DisposableServer server = httpServer.bindNow();
		if (serverMixIn.getHttpPort() == null) {
			System.out.println("Litening on port: " + server.port());
		}
		
		Runtime.getRuntime().addShutdownHook(
				new Thread(() -> {
					System.out.println("Shutting down HTTP Server");
					server.disposeNow(Duration.ofSeconds(timeout));
					if (routeBuilders != null) {
						System.out.println("Closing routes");
						for (HttpServerRouteBuilder routeBuilder: routeBuilders) {
							if (routeBuilder instanceof AutoCloseable) {
								try {
									((AutoCloseable) routeBuilder).close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}						
					}
				}, 
				"HTTP Server Shutdown hook"));
		
		server.onDispose().block();
		return 0;
	}

}
