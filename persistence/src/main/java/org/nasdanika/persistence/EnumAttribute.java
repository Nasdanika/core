package org.nasdanika.persistence;

import java.util.Collection;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

public class EnumAttribute<T extends Enum<T>> extends Attribute<T> {

	private Class<T> type;

	public EnumAttribute(
			Object key, 
			Class<T> type, 
			boolean isDefault,
			boolean isConstructor, 
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.type = type;
	}
	
	@Override
	public T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		try {
			return Enum.valueOf(type, (String) config);
		} catch (IllegalArgumentException e) {
			throw new ConfigurationException(e.getMessage(),  e, markers);
		}
	}

}
