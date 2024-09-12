package org.nasdanika.graph.processor;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;

/**
 * If element is an instance of {@link PropertySource} then {@link URI} is retrieved from it - property value should either be a URI or a String. 
 * @param <P>
 */
public class PropertySourceProcessorFactory<P> extends URIInvocableCapabilityProcessorFactory<P> {
	protected CapabilityLoader capabilityLoader;
	private String propertyName;
	private URI base;

	public PropertySourceProcessorFactory(String propertyName, URI base) {
		this.propertyName = propertyName;
		this.base = base;
	}
	
	public PropertySourceProcessorFactory(CapabilityLoader capabilityLoader, String propertyName, URI base) {
		super(capabilityLoader);
		this.propertyName = propertyName;
		this.base = base;
	}

	@Override
	protected URI getURI(ProcessorConfig config, ProgressMonitor progressMonitor) {
		Element element = config.getElement();
		if (element instanceof PropertySource) {
			@SuppressWarnings("unchecked")
			Object property = ((PropertySource<Object,Object>) element).getProperty(propertyName);
			if (property instanceof URI) {
				URI uri = (URI) property;
				return base == null ? uri : uri.resolve(base);
			}
			if (property instanceof String) {
				URI uri = URI.createURI((String) property);
				return base == null ? uri : uri.resolve(base);
			}
		}
		return null;
	}

}
