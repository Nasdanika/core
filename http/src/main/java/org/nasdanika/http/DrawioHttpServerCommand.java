package org.nasdanika.http;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.ProgressMonitorMixIn;
import org.nasdanika.common.Component;
import org.nasdanika.common.Description;
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

@Command(
		description = "Routes HTTP requests to a diagram element processor",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "http-server")
@ParentCommands(Document.Supplier.class)
@Description(icon = "https://docs.nasdanika.org/images/http.svg")
public class DrawioHttpServerCommand extends AbstractHttpServerCommand {
	
	public DrawioHttpServerCommand() {
		super();
	}

	public DrawioHttpServerCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@ParentCommand
	private Document.Supplier documentSupplier;
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"Processor property"
		})
	@Description
	private String processorProperty;
		
	@Parameters(
		index =  "1",	
		arity = "1",
		description = {  
			"Route property"
		})
	@Description
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
			
			ElementProcessorFactory<Object,Object,Object> elementProcessorFactory = new ElementProcessorFactory<Object,Object,Object>(
					document, 
					getCapabilityLoader(), 
					processorProperty) {

				/**
				 * This override is needed to collect processors implementing {@link AutoCloseable}
				 */
				@Override
				protected Object doCreateProcessor(
						ProcessorConfig<Object,Object> config, 
						boolean parallel,
						BiConsumer<org.nasdanika.graph.Element, BiConsumer<ProcessorInfo<Object,Object,Object>, ProgressMonitor>> infoProvider,
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
			Map<Element, ProcessorInfo<Object,Object,Object>> processors = elementProcessorFactory.createProcessors(endpointFactory , connectionBase, progressMonitor);
			
			// Starting
			start(document, components, progressMonitor);
			
			startServer(routes -> HttpServerRouteBuilder.buildRoutes(processors.values(), routeProperty, routes));
			
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
			
			return 0;
		}
	}

//	.route()
	
	
}
