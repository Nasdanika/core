package org.nasdanika.common.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * If config is a map loads each entry value using createValue().
 * @author Pavel
 *
 * @param <V>
 */
public class MapAttribute<K, V> extends Attribute<Map<K, V>> {

	public MapAttribute(
			Object key, 
			boolean isDefault,
			boolean required, 
			Map<K,V> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
	}
	
	@Override
	public Map<K,V> create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception {
		if (config instanceof Map) {
			Map<K,V> ret = new LinkedHashMap<>();
			for (Entry<?, ?> e: ((Map<?,?>) config).entrySet()) {
				K key = createKey(e.getKey());
				ret.put(key, createValue(loader, key, e.getValue(), base, progressMonitor, Util.getMarkers((Map<?,?>) config, e.getKey())));
			}
			return ret;
		}
		
		throw new ConfigurationException("Config should be a map: " + config, markers);
	}
	
	@SuppressWarnings("unchecked")
	protected K createKey(Object key) {
		return (K) key;
	}

	/**
	 * Creates entry value.
	 * @param loader
	 * @param value
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected V createValue(ObjectLoader loader, K key, Object value, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception { 
		return (V) value; 
	}
	
}
