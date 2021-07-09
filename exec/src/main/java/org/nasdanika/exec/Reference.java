package org.nasdanika.exec;

import java.net.URL;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Not interpolated reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class Reference implements Adaptable, Marked {
	
	public static final String CLASSPATH_URL_PREFIX = "classpath://";
	
	protected Object target;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public Reference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			String configStr = (String) config;			
			URL targetURL = configStr.startsWith(CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
			target = loader.loadYaml(targetURL, progressMonitor);
			this.marker = marker;
		} else {
			throw new ConfigurationException("Reference type must be a string", marker);
		}
	}
	
	public Reference(Marker marker, Object target) {
		this.marker = marker;
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {
		if (type.isInstance(target)) {
			return (T) target;
		}
		if (target instanceof Adaptable) {
			return ((Adaptable) target).adaptTo(type);
		}
		
		// Handling collections etc by delegating to Loader utility methods for Consumer and Supplier factories
		if (type == ConsumerFactory.class) {
			try {
				return (T) Util.asConsumerFactory(target, marker);
			} catch (Exception e) {
				throw new ConfigurationException("Could not load " + target.getClass() + " as ConsumerFactory", e, marker);
			}
		}
		
		if (type == SupplierFactory.class) {
			try {
				return (T) Util.asInputStreamSupplierFactory(target);
			} catch (Exception e) {
				throw new ConfigurationException("Could not load " + target.getClass() + " as SupplierFactory", e, marker);
			}
		}
		
		throw new ConfigurationException("Target " + target.getClass() + " is not of requested type and cannot be adapted to it: " + type, marker);
	}		

}
