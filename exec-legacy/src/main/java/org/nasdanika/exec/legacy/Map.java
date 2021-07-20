package org.nasdanika.exec;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Treats content as a map even if there is a single entry, which by default would be treated as an object declaration type to config. 
 * @author Pavel
 *
 */
public class Map implements Marked {

	protected java.util.Map<String,Object> map = new LinkedHashMap<>();
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

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
			this.marker = marker;
			for (Entry<String, Object> e: ((java.util.Map<String,Object>) config).entrySet()) {
				map.put(e.getKey(), loader.load(e.getValue(), base, progressMonitor));
			}
		} else {
			throw new ConfigurationException("(map) specification must be a map.", marker);
		}
	}
	
	public Map(Marker marker, java.util.Map<String,Object> map) {
		this.marker = marker;
		this.map.putAll(map);
	}
	
	public java.util.Map<String, Object> getMap() {
		return map;
	}
	
}
