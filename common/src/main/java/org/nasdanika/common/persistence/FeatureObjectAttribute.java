package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.function.Supplier;

import org.nasdanika.common.ProgressMonitor;

public class FeatureObjectAttribute<T extends FeatureObject> extends Attribute<T> {

	private Supplier<T> factory;

	public FeatureObjectAttribute(
			Object key,
			Supplier<T> factory,
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.factory = factory;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		T ret = factory.get();
		ret.load(loader, config, base, progressMonitor, marker);
		return ret;
	}

}
