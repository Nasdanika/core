package org.nasdanika.http;

import java.time.Duration;
import java.util.function.Consumer;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.CommandBase;

import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServerRoutes;

public abstract class AbstractHttpServerCommand extends CommandBase {

	protected AbstractHttpServerCommand() {
		super();
	}

	protected AbstractHttpServerCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
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
	
	@Option(
			names = "--console",
			negatable = true,
			description = {
				"If true starts a console and waits for exit command, ",
				"otherwise creates a shutdown hook disposing the server"
			})
	private boolean console;

	protected void startServer(Consumer<? super HttpServerRoutes> routesBuilder) throws Exception {
		// TODO - start route builders capability providers
		
		DisposableServer server = serverMixIn
				.createServer()
				.route(routesBuilder)
				.bindNow();
		
		if (serverMixIn.getHttpPort() == null) {
			System.out.println("Litening on port: " + server.port());
		}		
		
		if (console) {
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
		} else {        
			Runtime.getRuntime().addShutdownHook(
					new Thread(() -> {
						System.out.println("Shutting down HTTP Server");
						server.disposeNow(Duration.ofSeconds(timeout));
					}, 
					"HTTP Server Shutdown hook"));
			
			server.onDispose().block();
		}
	}

}
