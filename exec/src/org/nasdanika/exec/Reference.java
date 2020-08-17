package org.nasdanika.exec;

import java.net.URL;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;

/**
 * Not interpolated reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class Reference implements Adaptable {
	
	private Object target;

	public Reference(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			URL targetURL = new URL(base, (String) config);
			target = loader.loadYaml(targetURL, progressMonitor);
		} else {
			throw new ConfigurationException("Reference type must be a string", marker);
		}
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
		throw new NasdanikaException("Target " + target + " is not of requested type and cannot be adapted to it: " + type);
	}		

}
