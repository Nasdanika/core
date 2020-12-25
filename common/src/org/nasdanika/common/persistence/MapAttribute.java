package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * If config is a list loads each element using createElement(), otherwise creates a singleton loaded with createElement()
 * @author Pavel
 *
 * @param <T>
 */
public class MapAttribute<T> extends Attribute<Map<?, T>> {

	public MapAttribute(Object key, boolean required, Map<?,T> defaultValue, Object... exclusiveWith) {
		super(key, false, required, defaultValue, null, exclusiveWith);
	}
	
	@Override
	public Map<?,T> create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
		if (config instanceof Map) {
			Map<Object,T> ret = new LinkedHashMap<>();
			for (Entry<?, ?> e: ((Map<?,?>) config).entrySet()) {
				ret.put(e.getKey(), createValue(loader, e.getValue(), base, progressMonitor, Util.getMarker((Map<?,?>) config, e.getKey())));
			}
			return ret;
		}
		
		throw new ConfigurationException("Config should be a map: " + config, marker);
	}

	/**
	 * Creates element list.
	 * @param loader
	 * @param element
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected T createValue(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception { 
		return (T) element; 
	}
	
}
