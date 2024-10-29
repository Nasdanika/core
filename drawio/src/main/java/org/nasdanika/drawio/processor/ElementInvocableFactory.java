package org.nasdanika.drawio.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;


public class ElementInvocableFactory extends ElementProcessorFactory<Invocable> {

	public ElementInvocableFactory(org.nasdanika.drawio.Element element, CapabilityLoader capabilityLoader, String processorProperty) {
		super(element, capabilityLoader, processorProperty);
	}

	public ElementInvocableFactory(org.nasdanika.drawio.Element element, String processorProperty) {
		super(element, processorProperty);
	}
		
	/**
	 * 
	 * @param <H>
	 * @param <E>
	 * @param <T>
	 * @param bindProperty
	 * @param endpointFactory
	 * @param connectionBase
	 * @param processorFilter Optional processor filter - can wrap a processor or provide a default processor
	 * @param progressMonitor
	 * @param classLoader
	 * @param interfaces
	 * @return
	 */
	public <H,E,T> T createProxy(
			String bindProperty,
			EndpointFactory<H, E> endpointFactory, 
			ConnectionBase connectionBase,
			Function<ProcessorInfo<Invocable>,Invocable> processorFilter,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		Map<Element, ProcessorInfo<Invocable>> processors = createProcessors(endpointFactory, connectionBase, progressMonitor);		
		return Invocable.createProxy(classLoader, (proxy, nameOrSignature) -> resolve(proxy, nameOrSignature, processors, processorFilter, bindProperty), interfaces);		
	}	
	
	protected Invocable resolve(
			Object proxy,
			String nameOrSignature, 
			Map<Element, ProcessorInfo<Invocable>> processors,
			Function<ProcessorInfo<Invocable>,Invocable> processorFilter,
			String bindProperty) {
		
		List<Invocable> matches = new ArrayList<>();
		for (Entry<Element, ProcessorInfo<Invocable>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof PropertySource) { 
				@SuppressWarnings("unchecked")
				String bindValue = ((PropertySource<String,String>) pe.getKey()).getProperty(bindProperty);
				if (Objects.equals(nameOrSignature, bindValue)) {
					ProcessorInfo<Invocable> processorInfo = pe.getValue();
					Invocable processor = processorFilter == null ?  processorInfo.getProcessor() : processorFilter.apply(processorInfo);
					if (processor != null) {
						matches.add(processor.bind(proxy));
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
			Function<ProcessorInfo<Invocable>,Invocable> processorFilter,
			ProgressMonitor progressMonitor,
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty, 
				endpointFactory,
				connectionBase,
				processorFilter,
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
			Function<ProcessorInfo<Invocable>,Invocable> processorFilter,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty,
				EndpointFactory.nopEndpointFactory(),
				connectionBase,
				processorFilter,
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
			Function<ProcessorInfo<Invocable>,Invocable> processorFilter,
			ProgressMonitor progressMonitor,
			Class<?>... interfaces) {
		
		return createProxy(
				bindProperty,
				connectionBase,
				processorFilter,
				progressMonitor,
				Thread.currentThread().getContextClassLoader(),
				interfaces);
	}	
	
}
