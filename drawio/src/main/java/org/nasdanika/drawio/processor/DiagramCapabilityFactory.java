package org.nasdanika.drawio.processor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.xml.parsers.ParserConfigurationException;

import org.nasdanika.capability.AbstractCapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.requirements.DiagramRequirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.Element;
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
			Document document;
			if (requirement.uri() != null) {
				document = Document.load(requirement.uri(), requirement.uriHandler(), requirement.propertySource());
			} else {
				document = Document.load(requirement.source(), requirement.base(), requirement.uriHandler(), requirement.propertySource());
			}
			if (requirement.processor() == null) {
				// Just document
				return wrap(document);
			}
			
			ElementInvocableFactory elementInvocableFactory = new ElementInvocableFactory(requirement.selector() == null ? document : (org.nasdanika.drawio.Element) requirement.selector().apply(document), requirement.processor());
			if (requirement.bind() == null) {
				// Processors
				Map<Element, ProcessorInfo<Object>> processors = elementInvocableFactory.createProcessors(null, false, progressMonitor);						
				return wrap(processors);
			}
			
			java.util.function.Function<Object,Object> proxy = elementInvocableFactory.createProxy(
					requirement.bind(),
					null,
					null,
					progressMonitor,
					requirement.classLoader(),
					requirement.interfaces());
			return wrap(proxy);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			return wrapError(e);
		}
	}

}
