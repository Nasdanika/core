package org.nasdanika.drawio.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;


public class DocumentInvocableFactory extends DocumentProcessorFactory<Invocable> {

	public DocumentInvocableFactory(Document document, CapabilityLoader capabilityLoader, String processorProperty) {
		super(document, capabilityLoader, processorProperty);
	}

	public DocumentInvocableFactory(Document document, String processorProperty) {
		super(document, processorProperty);
	}
		
	public <H,E,T> T createProxy(
			String bindProperty,
			EndpointFactory<H, E> endpointFactory, 
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		Map<Element, ProcessorInfo<Invocable>> processors = createProcessors(endpointFactory, connectionBase, progressMonitor);		
		return Invocable.createProxy(classLoader, nameOrSignature -> resolve(nameOrSignature, processors, bindProperty), interfaces);		
	}	
	
	protected Invocable resolve(
			String nameOrSignature, 
			Map<Element, ProcessorInfo<Invocable>> processors,
			String bindProperty) {
		
		List<Invocable> matches = new ArrayList<>();
		for (Entry<Element, ProcessorInfo<Invocable>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof PropertySource) { 
				@SuppressWarnings("unchecked")
				String bindValue = ((PropertySource<String,String>) pe.getKey()).getProperty(bindProperty);
				if (Objects.equals(nameOrSignature, bindValue)) {
					Invocable processor = pe.getValue().getProcessor();
					if (processor != null) {
						matches.add(processor);
					}
				}
			}
		}
		return matches.isEmpty() ? null : Invocable.of(matches.toArray(size -> new Invocable[size]));
	}
	
	public <H,E,T> T createProxy(
			String bindProperty,
			EndpointFactory<H, E> endpointFactory, 
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor,
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty, 
				endpointFactory,
				connectionBase,
				progressMonitor,
				Thread.currentThread().getContextClassLoader(),
				interfaces);
	}	
	
	/**
	 * Creates processors with NOP endpoint factory
	 * @param source
	 * @param processorProperty
	 * @param parallel
	 * @param progressMonitor
	 * @return
	 */
	public <T> T createProxy(
			String bindProperty,
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty,
				EndpointFactory.nopEndpointFactory(),
				connectionBase,
				progressMonitor,
				classLoader,
				interfaces);
	}
	
	/**
	 * Creates processors with NOP endpoint factory
	 * @param source
	 * @param processorProperty
	 * @param parallel
	 * @param progressMonitor
	 * @return
	 */
	public <T> T createProxy(
			String bindProperty,
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor,
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty,
				connectionBase,
				progressMonitor,
				Thread.currentThread().getContextClassLoader(),
				interfaces);
	}	
	
}
