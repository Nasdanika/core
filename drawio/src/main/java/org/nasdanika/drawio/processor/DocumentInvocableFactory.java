package org.nasdanika.drawio.processor;

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
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.ProcessorInfo;


public class DocumentInvocableFactory<P> extends DocumentProcessorFactory<Invocable> {

	public DocumentInvocableFactory(Document document, CapabilityLoader capabilityLoader, String processorProperty) {
		super(document, capabilityLoader, processorProperty);
	}

	public DocumentInvocableFactory(Document document, String processorProperty) {
		super(document, processorProperty);
	}
		
	public <H,E,T> T createProxy(
			Collection<? extends Element> source,
			String bindProperty,
			EndpointFactory<H, E> endpointFactory, 
			boolean parallel,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) throws Exception {
		
		Map<Element, ProcessorInfo<Invocable>> processors = createProcessors(source, endpointFactory, parallel, progressMonitor);		
		return Invocable.createProxy(classLoader, (method, args) -> resolve(method, args, processors, bindProperty), interfaces);		
	}	
	
	protected boolean match(String propertyValue, Method method, Object args) {
		if (method.getName().equals(propertyValue)) {
			return true;
		}
		
		StringBuilder signatureBuilder = new StringBuilder(method.getName()).append("(");		
		Class<?>[] pt = method.getParameterTypes();
		for (int i = 0; i < pt.length; ++i) {
			if (i > 0) {
				signatureBuilder.append(",");
			}
			signatureBuilder.append(pt[i]);
		}
		return signatureBuilder.append(")").toString().equals(propertyValue);
	}
	
	protected Invocable resolve(
			Method method, 
			Object[] args, 
			Map<Element, ProcessorInfo<Invocable>> processors,
			String bindProperty) {
		
		List<Invocable> matches = new ArrayList<>();
		for (Entry<Element, ProcessorInfo<Invocable>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof PropertySource) { 
				@SuppressWarnings("unchecked")
				String bindValue = ((PropertySource<String,String>) pe.getKey()).getProperty(bindProperty);
				if (match(bindValue, method, args)) {
					Invocable processor = pe.getValue().getProcessor();
					if (processor != null) {
						matches.add(processor);
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
	 * @throws Exception
	 */
	public <T> T createProxy(
			Collection<? extends Element> source,
			String bindProperty,
			boolean parallel,
			ProgressMonitor progressMonitor,
			ClassLoader classLoader, 			 
			Class<?>... interfaces) throws Exception {
		
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
