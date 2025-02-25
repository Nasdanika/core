package org.nasdanika.http;

import java.util.Collection;

import org.nasdanika.cli.Description;

import picocli.CommandLine.Command;
import reactor.netty.http.server.HttpServerRoutes;

@Command(
		description = "Serves HTTP routes",
		name = "http-server")
@Description(icon = "https://docs.nasdanika.org/images/http.svg")
public class HttpServerCommand extends AbstractHttpServerCommand {
	
	private Collection<HttpServerRouteBuilder> routeBuilders;

	public HttpServerCommand(Collection<HttpServerRouteBuilder> routeBuilders) {
		this.routeBuilders = routeBuilders;
	}

	protected void buildRoutes(HttpServerRoutes routes) {
		if (routeBuilders != null) {
			for (HttpServerRouteBuilder routeBuilder: routeBuilders) {
				routeBuilder.buildRoutes(routes);
			}
		}
	}
	
	@Override
	public Integer call() throws Exception {
		startServer(this::buildRoutes);
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
		return 0;
	}

}
