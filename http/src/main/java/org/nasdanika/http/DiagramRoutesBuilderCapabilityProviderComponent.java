package org.nasdanika.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.capability.CapabilityProviderComponent;
import org.nasdanika.common.Component;
import org.nasdanika.common.ExclusiveRealm;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Realm;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.drawio.processor.ElementProcessorFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.xml.sax.SAXException;

import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServerRoutes;

public class DiagramRoutesBuilderCapabilityProviderComponent extends CapabilityProviderComponent<HttpServerRouteBuilder> {
	
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
	
	protected Map<org.nasdanika.graph.Element,Component> components = new HashMap<>();
	
	protected Document document;

	private String routeProperty; 	
	
	private Map<Element, ProcessorInfo<Object,Object,Object,Object>> processors;
	
	public DiagramRoutesBuilderCapabilityProviderComponent(
			URI documentURI,
			Function<Document, org.nasdanika.drawio.Element<?>> selector,
			String processorProperty,
			String routeProperty,
			Loader loader,
			Function<URI, InputStream> uriHandler,
			Function<String, String> propertySource,
			ConnectionBase connectionBase,
			EndpointFactory<Object,Object> endpointFactory,
			ProgressMonitor progressMonitor) throws IOException, ParserConfigurationException, SAXException, URISyntaxException {
		
		this.routeProperty = routeProperty;
		
		Document.Context context = new Document.Context() {
			
			private Realm realm = new ExclusiveRealm();

			@Override
			public Realm getRealm() {
				return realm;
			}
			
			@Override
			public String getProperty(String name) {
				return propertySource == null ? Context.super.getProperty(name) : propertySource.apply(name);
			}
			
			@Override
			public InputStream openStream(URI uri) throws IOException, URISyntaxException {
				return uriHandler == null ? Context.super.openStream(uri) : uriHandler.apply(uri);
			}
			
			
		};
		
		document = Document.load(documentURI, context); 
		
		org.nasdanika.drawio.Element<?> element = selector == null ? document : selector.apply(document);
		ElementProcessorFactory<Object,Object,Object,Object> elementProcessorFactory = new ElementProcessorFactory<Object,Object,Object,Object>(
				element , 
				loader.getCapabilityLoader(), 
				processorProperty) {

			/**
			 * This override is needed to collect processors implementing {@link Component}
			 */
			@Override
			protected Object doCreateProcessor(
					ProcessorConfig<Object,Object,Object> config, 
					boolean parallel,
					BiConsumer<org.nasdanika.graph.Element, BiConsumer<ProcessorInfo<Object,Object,Object,Object>, ProgressMonitor>> infoProvider,
					Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
					ProgressMonitor progressMonitor) {
				
				Object processor = super.doCreateProcessor(
						config, 
						parallel, 
						infoProvider, 
						endpointWiringStageConsumer, 
						progressMonitor);
				
				if (processor instanceof Component) {
					components.put(config.getElement(), (Component) processor);
				}
				return processor;
			}
			
		};
		
		processors = elementProcessorFactory.createProcessors(
				endpointFactory, 
				connectionBase, 
				progressMonitor);
	}
	
	private void buildRoutes(HttpServerRoutes routes) {
		HttpServerRouteBuilder.buildRoutes(processors.values(), routeProperty, routes);	
	}	
	
	@Override
	public void start(ProgressMonitor progressMonitor) {
		super.start(progressMonitor);
		start(document, components, progressMonitor);
	}

	@Override
	public Flux<HttpServerRouteBuilder> getPublisher() {
		return Flux.just((HttpServerRouteBuilder) this::buildRoutes);
	}
	
	@Override
	public void stop(ProgressMonitor progressMonitor) {
		document.accept(e -> {
			Component component = components.get(e);
			if (component != null) {
				component.stop(progressMonitor);
			}
		});
		super.stop(progressMonitor);		
	}
	
	@Override
	public void close(ProgressMonitor progressMonitor) {
		document.accept(e -> {
			Component component = components.get(e);
			if (component != null) {
				component.close(progressMonitor);
			}
		});
		super.close(progressMonitor);
	}

}
