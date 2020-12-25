package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

public class Attribute<T> implements Feature<T>, ObjectFactory<T> {
	
	private Marker marker;
	private T value;
	private boolean loaded;
	private Object key;
	private boolean required;
	private Object[] exclusiveWith;
	
	public Attribute(Object key, boolean required, T defaultValue, Object... exclusiveWith) {
		this.key = key;
		this.required = required;
		this.value = defaultValue;		
		this.exclusiveWith = exclusiveWith;
	}
	
	public Object getKey() {
		return key;
	}
	
	public T getValue() {
		return value; 
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public void load(ObjectLoader loader, Map<?,?> source, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (source.containsKey(getKey())) {
			for (Object ek: exclusiveWith) {
				if (source.containsKey(ek)) {
					throw new ConfigurationException("Features '" + getKey() + " and " + ek + " are mutually exclusive", marker);					
				}
			}
			this.marker = Util.getMarker(source, getKey());
			value = create(loader, source.get(getKey()), base, progressMonitor, this.marker);						
			loaded = true;
		} else if (required) {
			throw new ConfigurationException("Missing required feature: " + getKey(), marker);
		}		
	}	
	
	/**
	 * This implementation casts config to the target type. It can be used where not special conversion is required, e.g. config is a String and feature type is String.
	 * @param loader
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return (T) config;
	}

	public Object[] getExclusiveWith() {
		return exclusiveWith;
	}
	
	public boolean isRequired() {
		return required;
	}
	
}
