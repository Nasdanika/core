package org.nasdanika.drawio.processor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.AbstractCapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.requirements.DiagramRequirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.xml.sax.SAXException;

public class DiagramCapabilityFactory extends AbstractCapabilityFactory<DiagramRequirement, Object> {

	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof DiagramRequirement;
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<Object>>> create(
			DiagramRequirement requirement,
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		try {
			Document.Context requirementContext = new Document.Context() {
				
				@Override
				public InputStream openStream(URI uri) throws IOException, URISyntaxException {
					return requirement.uriHandler() == null ? Context.super.openStream(uri) : requirement.uriHandler().apply(uri);
				}
				
				@Override
				public String getProperty(String name) {
					return requirement.propertySource() == null ? Context.super.getProperty(name) : requirement.propertySource().apply(name);
				}
				
			};
			
			Document document;
			if (requirement.uri() != null) {
				document = Document.load(requirement.uri(), requirementContext);
			} else {
				document = Document.load(requirement.source(), requirement.base(), requirementContext);
			}
			if (requirement.processor() == null) {
				// Just document
				return wrap(document);
			}
			
			ElementInvocableFactory<Object,Object> elementInvocableFactory = new ElementInvocableFactory<>(requirement.selector() == null ? document : (org.nasdanika.drawio.Element) requirement.selector().apply(document), requirement.processor());
			if (requirement.bind() == null) {
				// Processors
				Map<Element, ProcessorInfo<Object,Object,Object>> processors = elementInvocableFactory.createProcessors(null, false, progressMonitor);						
				return wrap(processors);
			}
			
			EndpointFactory<Object,Object> endpointFactory = EndpointFactory.nopEndpointFactory();
			java.util.function.Function<Object,Object> proxy = elementInvocableFactory.createProxy(
					requirement.bind(),
					endpointFactory,
					null, 
					progressMonitor,
					requirement.classLoader(),
					requirement.interfaces());
			return wrap(proxy);
		} catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
			return wrapError(e);
		}
	}

}
