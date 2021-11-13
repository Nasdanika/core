package org.nasdanika.common.persistence;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;

/**
 * Delegates feature creation to a factory, e.g. a constructor.
 * @author Pavel
 *
 */
public class FactoryFeature<T> extends Attribute<T> {

	private ObjectFactory<T> factory;

	public FactoryFeature(
			Object key,
			boolean isDefault,
			boolean required, 
			T defaultValue,
			String description,
			ObjectFactory<T> factory, 
			Object... exclusiveWith) {
		
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.factory = factory;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
		return factory.create(loader, config, base, progressMonitor, marker);
	}

}
