package org.nasdanika.drawio.processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.PropertySourceProcessorFactory;

/**
 * Creates processors for {@link org.nasdanika.drawio.Element}.
 * @param <P>
 */
public class ElementProcessorFactory<P> extends PropertySourceProcessorFactory<P,String,String> {

	protected org.nasdanika.drawio.Element element;

	public ElementProcessorFactory(
			org.nasdanika.drawio.Element element, 
			CapabilityLoader capabilityLoader, 
			String processorProperty) {
		super(capabilityLoader, processorProperty);
		this.element = element;
	}

	public ElementProcessorFactory(
			org.nasdanika.drawio.Element element, 
			String processorProperty) {
		super(processorProperty);
		this.element = element;
	}
	
	/**
	 * Creates procssor configs and then processors.
	 * @param <H>
	 * @param <E>
	 * @param endpointFactory
	 * @param processorProperty
	 */
	public <H,E> Map<Element, ProcessorInfo<P>> createProcessors(
			EndpointFactory<H, E> endpointFactory, 
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor) {
		
		Collection<Element> elements = new HashSet<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.traverser(elements::add, ConnectionBase.SOURCE);
		element.accept(consumer, connectionBase);
		
		return createProcessors(
				elements,
				endpointFactory, 
				false,
				progressMonitor);
	}	
	
	/**
	 * Creates processors with NOP endpoint factory
	 * @param source
	 * @param processorProperty
	 * @param parallel
	 * @param progressMonitor
	 * @return
	 */
	public Map<Element, ProcessorInfo<P>> createNopEndpointProcessors(
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor) {
		
		return createProcessors(
				EndpointFactory.nopEndpointFactory(),
				connectionBase,
				progressMonitor);
	}

	@Override
	protected URI getURI(String value) {
		if (!Util.isBlank(value)) {
			URI uri = URI.createURI((String) value);
			URI base = element.getURI();
			return base == null ? uri : uri.resolve(base);
		}
		return null;
	}
		
}
