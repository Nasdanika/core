package org.nasdanika.drawio.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.PropertySourceProcessorFactory;


public class DocumentProcessorFactory<P> extends PropertySourceProcessorFactory<P,String,String> {

	protected Document document;

	public DocumentProcessorFactory(Document document, CapabilityLoader capabilityLoader, String processorProperty) {
		super(capabilityLoader, processorProperty);
		this.document = document;
	}

	public DocumentProcessorFactory(Document document, String processorProperty) {
		super(processorProperty);
		this.document = document;
	}
	
	/**
	 * Creates procssor configs and then processors.
	 * @param <H>
	 * @param <E>
	 * @param endpointFactory
	 * @param processorProperty
	 * @throws Exception
	 */
	public <H,E> Map<Element, ProcessorInfo<P>> createProcessors(
			EndpointFactory<H, E> endpointFactory, 
			ConnectionBase connectionBase,
			boolean parallel,
			ProgressMonitor progressMonitor) throws Exception {
		
		Collection<Element> elements = new ArrayList<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.withLinkTargets(elements::add, ConnectionBase.SOURCE);
		document.accept(consumer, connectionBase);
		
		return createProcessors(
				elements,
				endpointFactory, 
				parallel,
				progressMonitor);
	}	
	
	/**
	 * Creates processors with NOP endpoint factory
	 * @param source
	 * @param processorProperty
	 * @param parallel
	 * @param progressMonitor
	 * @return
	 * @throws Exception
	 */
	public Map<Element, ProcessorInfo<P>> createNopEndpointProcessors(
			ConnectionBase connectionBase,
			boolean parallel,
			ProgressMonitor progressMonitor) throws Exception {
		
		return createProcessors(
				EndpointFactory.nopEndpointFactory(),
				connectionBase,
				parallel,
				progressMonitor);
	}

	@Override
	protected URI getURI(String value) {
		if (!Util.isBlank(value)) {
			URI uri = URI.createURI((String) value);
			URI base = document.getURI();
			return base == null ? uri : uri.resolve(base);
		}
		return null;
	}
		
}
