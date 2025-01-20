package org.nasdanika.http;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.CommandBase;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.ProgressMonitorMixIn;
import org.nasdanika.common.Component;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.processor.ElementProcessorFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;
import reactor.netty.DisposableServer;

@Command(
		description = "Routes HTTP requests to diagram element processor",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "http-server")
@ParentCommands(Document.Supplier.class)
public class DrawioHttpServerCommand extends CommandBase {
	
	public DrawioHttpServerCommand() {
		super();
	}

	public DrawioHttpServerCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}
	
	@Mixin
	private HttpServerMixIn serverMixIn;	

	@ParentCommand
	private Document.Supplier documentSupplier;
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"Processor property"
		})
	private String processorProperty;
		
	@Parameters(
		index =  "1",	
		arity = "1",
		description = {  
			"Route property"
		})
	private String routeProperty;
		
	@Mixin
	protected ProgressMonitorMixIn progressMonitorMixIn;

	@Option(
			names = "--connection-base", 
			description = {
					"Connection base",
					"Valid values: ${COMPLETION-CANDIDATES}"})	
	private ConnectionBase connectionBase;
	
	private static void start(
			Element element, 
			Map<org.nasdanika.graph.Element,Component> components,
			ProgressMonitor progressMonitor) {
		Component component = components.get(element);
		if (component != null) {
			component.start(progressMonitor);
		}
		for (Element child: element.getChildren()) {
			start(child, components, progressMonitor);
		}		
	}

	@Override
	public Integer call() throws Exception {
		try (ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1)) {
			Document document = documentSupplier.getDocument(progressMonitor);
			
			Map<org.nasdanika.graph.Element,Component> components = new HashMap<>();
			
			ElementProcessorFactory<Object> elementProcessorFactory = new ElementProcessorFactory<Object>(
					document, 
					getCapabilityLoader(), 
					processorProperty) {

				/**
				 * This override is needed to collect processors implementing {@link AutoCloseable}
				 */
				@Override
				protected Object doCreateProcessor(
						ProcessorConfig config, 
						boolean parallel,
						BiConsumer<org.nasdanika.graph.Element, BiConsumer<ProcessorInfo<Object>, ProgressMonitor>> infoProvider,
						Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
						ProgressMonitor progressMonitor) {
					
					Object processor = super.doCreateProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
					if (processor instanceof Component) {
						components.put(config.getElement(), (Component) processor);
					}
					return processor;
				}
				
			};
			
			EndpointFactory<Object,Object> endpointFactory = null; // TODO - option, invocable.
			Map<Element, ProcessorInfo<Object>> processors = elementProcessorFactory.createProcessors(endpointFactory , connectionBase, progressMonitor);
			
			// Starting
			start(document, components, progressMonitor);
			
			// Creating server
			DisposableServer server = serverMixIn
					.createServer()
					.route(routes -> HttpServerRouteBuilder.buildRoutes(processors.values(), routeProperty, routes))
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
	        
	        server.dispose();			
	        server.onDispose().block();
			
			// Stopping 
			document.accept(e -> {
				Component component = components.get(e);
				if (component != null) {
					component.stop(progressMonitor);
				}
			});
			
			
			// Closing
			document.accept(e -> {
				Component component = components.get(e);
				if (component != null) {
					component.close(progressMonitor);
				}
			});
		}
		return 0;
	}		

}
