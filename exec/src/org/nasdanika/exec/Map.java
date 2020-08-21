package org.nasdanika.exec;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;

/**
 * Treats content as a map even if there is a single entry, which by default would be treated as an object declaration type to config. 
 * @author Pavel
 *
 */
public class Map {

	private java.util.Map<String,Object> map = new LinkedHashMap<>();

	/**
	 * Iterator config is a map of iterator values to objects to iterate over. I.e. one iterator (for-each) may contain multiple iteration "clauses". 
	 * @param factory
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker 
	 */
	@SuppressWarnings("unchecked")
	public Map(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof java.util.Map) {
			for (Entry<String, Object> e: ((java.util.Map<String,Object>) config).entrySet()) {
				map.put(e.getKey(), loader.load(e.getValue(), base, progressMonitor));
			}
		} else {
			throw new ConfigurationException("(map) specification must be a map.", marker);
		}
	}
	
	public java.util.Map<String, Object> getMap() {
		return map;
	}
	
}
