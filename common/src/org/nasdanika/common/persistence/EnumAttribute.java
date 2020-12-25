package org.nasdanika.common.persistence;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;

public class EnumAttribute<T extends Enum<T>> extends Attribute<T> {

	private Class<T> type;

	public EnumAttribute(Object key, Class<T> type, boolean required, T defaultValue, Object[] exclusiveWith) {
		super(key, false, required, defaultValue, null, exclusiveWith);
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
