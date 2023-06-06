package org.nasdanika.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

public class FeatureObjectAttribute<T extends FeatureObject> extends Attribute<T> {

	private Supplier<T> factory;

	public FeatureObjectAttribute(
			Object key,
			Supplier<T> factory,
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			T defaultValue, 
			String description,
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.factory = factory;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		T ret = factory.get();
		ret.load(loader, config, base, progressMonitor, markers);
		return ret;
	}

}
