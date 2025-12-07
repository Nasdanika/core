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
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;


public class ElementInvocableFactory<H,E,K> extends ElementProcessorFactory<H,E,K,Object> {

	public ElementInvocableFactory(org.nasdanika.drawio.Element<?> element, CapabilityLoader capabilityLoader, String processorProperty) {
		super(element, capabilityLoader, processorProperty);
	}

	public ElementInvocableFactory(org.nasdanika.drawio.Element<?> element, String processorProperty) {
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
	public <T> T createProxy(
			String bindProperty,
			EndpointFactory<H,E> endpointFactory, 
			ConnectionBase connectionBase,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		Map<Element, ProcessorInfo<H,E,K,Object>> processors = createProcessors(endpointFactory, connectionBase, progressMonitor);
		return Invocable.createProxy(classLoader, (proxy, nameOrSignature) -> resolve(proxy, nameOrSignature, processors, bindProperty), interfaces);		
	}	
	
	protected Invocable resolve(
			Object proxy,
			String nameOrSignature, 
			Map<Element, ProcessorInfo<H,E,K,Object>> processors,
			String bindProperty) {
		
		List<Invocable> matches = new ArrayList<>();
		for (Entry<Element, ProcessorInfo<H,E,K,Object>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof PropertySource) { 
				@SuppressWarnings("unchecked")
				String bindValue = ((PropertySource<String,String>) pe.getKey()).getProperty(bindProperty);
				if (Objects.equals(nameOrSignature, bindValue)) {
					ProcessorInfo<H,E,K,Object> processorInfo = pe.getValue();
					Object processor = processorInfo.getProcessor();
					if (processor instanceof Invocable) {
						matches.add(((Invocable) processor).bind(proxy));
					}
				}
			}
		}
		return matches.isEmpty() ? null : Invocable.of(matches.toArray(size -> new Invocable[size]));
	}
	
	public <T> T createProxy(
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
//	
//	/**
//	 * Creates processors with NOP endpoint factory
//	 * @param source
//	 * @param processorProperty
//	 * @param parallel
//	 * @param progressMonitor
//	 * @return
//	 */
//	public <T> T createProxy(
//			String bindProperty,
//			ConnectionBase connectionBase,
//			ProgressMonitor progressMonitor,
//			ClassLoader classLoader, 			 
//			Class<?>... interfaces) {
//		
//		return createProxy(
//				bindProperty,
//				EndpointFactory.nopEndpointFactory(),
//				connectionBase,
//				progressMonitor,
//				classLoader,
//				interfaces);
//	}
//	
//	/**
//	 * Creates processors with NOP endpoint factory
//	 * @param source
//	 * @param processorProperty
//	 * @param parallel
//	 * @param progressMonitor
//	 * @return
//	 */
//	public <T> T createProxy(
//			String bindProperty,
//			ConnectionBase connectionBase,
//			ProgressMonitor progressMonitor,
//			Class<?>... interfaces) {
//		
//		return createProxy(
//				bindProperty,
//				connectionBase,
//				progressMonitor,
//				Thread.currentThread().getContextClassLoader(),
//				interfaces);
//	}	
	
}
