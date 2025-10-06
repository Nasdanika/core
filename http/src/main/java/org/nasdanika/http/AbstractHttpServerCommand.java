package org.nasdanika.http;

import java.awt.Desktop;
import java.net.URI;
import java.time.Duration;
import java.util.function.Consumer;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.CommandBase;
import org.nasdanika.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServerRoutes;

public abstract class AbstractHttpServerCommand extends CommandBase {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpServerCommand.class);		

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
				"If true, starts a console and waits for the exit",
				"command, otherwise creates a shutdown hook",
				"disposing the server"
			})
	private boolean console;
	
	@Option(
			names = "--open",
			description = {
				"Opens provided URI in the system browser",
				"the URI is resolved relative to",
				"http://localhost:<port>/"
			})
	private String uriToOpen;
	
	protected String getUriToOpen() {
		return uriToOpen;
	}

	protected void startServer(Consumer<? super HttpServerRoutes> routesBuilder) throws Exception {
		// TODO - start route builders capability providers
		
		DisposableServer server = serverMixIn
				.createServer()
				.route(routesBuilder)
				.bindNow();
		
		System.out.println("Litening on port: " + server.port());
		LOGGER.info("Listening on port {}", server.port());
		
		if (!Util.isBlank(getUriToOpen()) 
				&& Desktop.isDesktopSupported() 
				&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			
		    URI resolvedUri = new URI("http://localhost:" + server.port() + "/").resolve(getUriToOpen());			
			LOGGER.info("Opening {} in the browser", resolvedUri);
			Desktop.getDesktop().browse(resolvedUri);
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
