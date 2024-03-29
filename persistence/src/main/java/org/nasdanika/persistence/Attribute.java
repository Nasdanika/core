package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
	private boolean isConstructor;
	private String description;
	
	public Attribute(
			Object key, 
			boolean isDefault, 
			boolean isConstructor,
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		this.key = key;
		this.isDefault = isDefault;
		this.isConstructor = isConstructor;
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
	public void load(
			ObjectLoader loader, 
			Map<?, ?> source, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		if (source.containsKey(getKey())) {
			for (Object ek: exclusiveWith) {
				if (source.containsKey(ek)) {
					throw new ConfigurationException("Features '" + getKey() + " and " + ek + " are mutually exclusive", markers);					
				}
			}
			this.markers = Util.getMarkers(source, getKey());
			value = create(loader, source.get(getKey()), base, resolver, this.markers, progressMonitor);						
			loaded = true;
		} else if (isRequired()) {
			throw new ConfigurationException("Missing required feature: " + getKey(), markers);
		}		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
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
	public boolean isConstructor() {
		return isConstructor;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
