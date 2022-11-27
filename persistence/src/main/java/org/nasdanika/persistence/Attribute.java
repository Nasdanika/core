package org.nasdanika.persistence;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

public class Attribute<T> implements Feature<T>, ObjectFactory<T> {
	
	private List<? extends Marker> markers;
	private T value;
	private boolean loaded;
	private Object key;
	private boolean required;
	private Object[] exclusiveWith;
	private boolean isDefault;
	private String description;
	
	public Attribute(
			Object key, 
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		this.key = key;
		this.isDefault = isDefault;
		this.required = required;
		this.value = defaultValue;
		this.description = description;
		this.exclusiveWith = exclusiveWith;		
	}
		
	@Override
	public Object getKey() {
		return key;
	}
	
	@Override
	public T getValue() {
		return value; 
	}
	
	@Override
	public boolean isLoaded() {
		return loaded;
	}
	
	@Override
	public List<? extends Marker> getMarkers() {
		return markers;
	}

	@Override
	public void load(ObjectLoader loader, Map<?,?> source, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		if (source.containsKey(getKey())) {
			for (Object ek: exclusiveWith) {
				if (source.containsKey(ek)) {
					throw new ConfigurationException("Features '" + getKey() + " and " + ek + " are mutually exclusive", markers);					
				}
			}
			this.markers = Util.getMarkers(source, getKey());
			value = create(loader, source.get(getKey()), base, progressMonitor, this.markers);						
			loaded = true;
		} else if (isRequired()) {
			throw new ConfigurationException("Missing required feature: " + getKey(), markers);
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
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		return (T) config;
	}

	public Object[] getExclusiveWith() {
		return exclusiveWith;
	}
	
	@Override
	public boolean isRequired() {
		return required;
	}
	
	@Override
	public boolean isDefault() {
		return isDefault;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
