package org.nasdanika.persistence;

import java.util.List;

import org.eclipse.emf.common.util.URI;
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
	public T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		return factory.create(loader, config, base, progressMonitor, markers);
	}

}
