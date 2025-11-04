package org.nasdanika.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.processor.EndpointFactory;
import org.xml.sax.SAXException;

/**
 * Loads a diagram wraps it into HttpServerRouteBuilder.
 * 
 */
public abstract class DiagramRoutesBuilderFactory extends ServiceCapabilityFactory<Void, HttpServerRouteBuilder> {
	
	private URI documentURI;
	private String processorProperty;
	private String routeProperty;

	protected DiagramRoutesBuilderFactory(
			URI documentURI,
			String processorProperty,
			String routeProperty) {
		this.documentURI = documentURI;
		this.processorProperty = processorProperty;
		this.routeProperty = routeProperty;
	}
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return HttpServerRouteBuilder.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<HttpServerRouteBuilder>>> createService(
			Class<HttpServerRouteBuilder> serviceType, 
			Void serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		try {
			DiagramRoutesBuilderCapabilityProviderComponent capabilityProvider = new DiagramRoutesBuilderCapabilityProviderComponent(
					documentURI, 
					this::selectElement,
					processorProperty, 
					routeProperty, 
					loader, 
					getUriHandler(loader, progressMonitor), 
					getPropertySource(loader, progressMonitor), 
					getConnectionBase(), 
					getEndpointFactory(loader, progressMonitor), 
					progressMonitor);
			return CompletableFuture.completedStage(Collections.singleton(capabilityProvider));
		} catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
			return wrapError(e);
		}		
	}

	protected Function<String, String> getPropertySource(Loader loader, ProgressMonitor progressMonitor) {
		return null;
	}
	
	protected Function<URI, InputStream> getUriHandler(Loader loader, ProgressMonitor progressMonitor) {
		return null;
	}
	
	protected ConnectionBase getConnectionBase() {
		return null;
	}
		
	protected EndpointFactory<Object,Object> getEndpointFactory(Loader loader, ProgressMonitor progressMonitor) {
		return null;
	}
	
	protected org.nasdanika.drawio.Element selectElement(Document document) {
		return document;
	}
	
}