package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
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
	public T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		T ret = factory.get();
		ret.load(loader, config, base, resolver, markers, progressMonitor);
		return ret;
	}

}
