package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * If element is an instance of {@link PropertySource} then {@link URI} is retrieved from it - property value should either be a URI or a String. 
 * @param <P>
 */
public abstract class PropertySourceProcessorFactory<H,E,K,P,R,V> extends URIInvocableCapabilityProcessorFactory<H,E,K,P> {
	protected CapabilityLoader capabilityLoader;
	private R processorProperty;

	public PropertySourceProcessorFactory(R processorProperty) {
		this.processorProperty = processorProperty;
	}
	
	public PropertySourceProcessorFactory(CapabilityLoader capabilityLoader, R processorProperty) {
		super(capabilityLoader);
		this.processorProperty = processorProperty;
	}

	@Override
	protected URI getURI(ProcessorConfig<H,E,K> config, ProgressMonitor progressMonitor) {
		return getURI(config.getElement());
	}

	protected URI getURI(Element element) {
		if (element instanceof PropertySource) {
			@SuppressWarnings("unchecked")
			V value = ((PropertySource<R,V>) element).getProperty(processorProperty);
			return getURI(value);
		}
		return null;
	}
	
	protected abstract URI getURI(V value);

	/**
	 * Creates procssor configs and then processors.
	 * @param <H>
	 * @param <E>
	 * @param endpointFactory
	 * @param processorProperty
	 */
	public Map<Element, ProcessorInfo<H,E,K,P>> createProcessors(
			Collection<? extends Element> source,
			EndpointFactory<H,E> endpointFactory, 
			boolean parallel,
			ProgressMonitor progressMonitor) {
		ProcessorConfigFactory<H,E,K> processorConfigFactory = new ProcessorConfigFactory<H,E,K>() {
			
			@Override
			protected boolean isPassThrough(Connection connection) {
				if (connection instanceof PropertySource) {
					return PropertySourceProcessorFactory.this.getURI(connection) == null;
				}
				
				return super.isPassThrough(connection);
			}

			@Override
			public E createEndpoint(Element element, H handler, HandlerType type) {
				return endpointFactory.createEndpoint(element, handler, type);
			}
			
		};
		
		Transformer<Element,ProcessorConfig<H,E,K>> processorConfigTransformer = new Transformer<>(processorConfigFactory);		
		Map<Element, ProcessorConfig<H,E,K>> configs = processorConfigTransformer.transform(source, parallel, progressMonitor);		
		return createProcessors(configs.values(), parallel, progressMonitor);
	}	
	
}
