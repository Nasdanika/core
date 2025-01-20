package org.nasdanika.http;

import java.time.Duration;
import java.util.Collection;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.nasdanika.cli.CommandBase;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import reactor.netty.DisposableServer;
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
		// TODO - start route builders capability providers
		
		DisposableServer server = serverMixIn
				.createServer()
				.route(this::buildRoutes)
				.bindNow();
		
		if (serverMixIn.getHttpPort() == null) {
			System.out.println("Litening on port: " + server.port());
		}
		
		// Command line
		// TODO - status commands
        try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {
        	LineReader lineReader = LineReaderBuilder
        			.builder()
                    .terminal(terminal)
                    .build();
        	
        	String prompt = "http-server>";
            while (true) {
                String line = null;
                line = lineReader.readLine(prompt);
                if ("exit".equals(line)) {
                	break;
                } else {
	                System.out.println("Type exit<Enter> to stop the server");	                	
                }
            }
        }
        
        server.disposeNow(Duration.ofSeconds(timeout));
        
        // TODO - stop and close capability providers
		return 0;
	}

}
