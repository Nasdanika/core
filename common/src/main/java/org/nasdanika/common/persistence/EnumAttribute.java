package org.nasdanika.common.persistence;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;

public class EnumAttribute<T extends Enum<T>> extends Attribute<T> {

	private Class<T> type;

	public EnumAttribute(
			Object key, 
			Class<T> type, 
			boolean isDefault,
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.type = type;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		try {
			return Enum.valueOf(type, (String) config);
		} catch (IllegalArgumentException e) {
			throw new ConfigurationException(e.getMessage(),  e, marker);
		}
	}

}
