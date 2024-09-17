package org.nasdanika.graph.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;

public abstract class PropertySourceInvocableFactory<K,V> extends PropertySourceProcessorFactory<Invocable,K,V> {

	protected PropertySourceInvocableFactory(CapabilityLoader capabilityLoader, K processorProperty) {
		super(capabilityLoader, processorProperty);
	}

	protected PropertySourceInvocableFactory(K processorProperty) {
		super(processorProperty);
	}
		
	public <H,E,T> T createProxy(
			Collection<? extends Element> source,
			K bindProperty,
			EndpointFactory<H, E> endpointFactory, 
			boolean parallel,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		Map<Element, ProcessorInfo<Invocable>> processors = createProcessors(source, endpointFactory, parallel, progressMonitor);		
		return Invocable.createProxy(classLoader, (proxy, method, args) -> resolve(proxy, method, args, processors, bindProperty), interfaces);		
	}	
	
	protected abstract boolean match(V propertyValue, Method method, Object args);
	
	protected Invocable resolve(
			Object proxy,
			Method method, 
			Object[] args, 
			Map<Element, ProcessorInfo<Invocable>> processors,
			K bindProperty) {
		
		List<Invocable> matches = new ArrayList<>();
		for (Entry<Element, ProcessorInfo<Invocable>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof PropertySource) { 
				@SuppressWarnings("unchecked")
				V bindValue = ((PropertySource<K,V>) pe.getKey()).getProperty(bindProperty);
				if (match(bindValue, method, args)) {
					Invocable processor = pe.getValue().getProcessor();
					if (processor != null) {
						matches.add(processor.bind(proxy)); // 
					}
				}
			}
		}
		return matches.isEmpty() ? null : Invocable.of(matches.toArray(size -> new Invocable[size]));
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
			Collection<? extends Element> source,
			K bindProperty,
			boolean parallel,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) {
		
		return createProxy(
				source,
				bindProperty,
				EndpointFactory.nopEndpointFactory(),
				parallel,
				progressMonitor,
				classLoader,
				interfaces);
	}	

}
