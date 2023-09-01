package org.nasdanika.persistence;

import java.util.Collection;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Loads feature value using loader.
 * @author Pavel
 *
 */
public class Reference extends Attribute<Object> {

	public Reference(
			Object key, 
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			Object defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
	}
	
	@Override
	public Object create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return loader.load(config, base, resolver, progressMonitor);
	}

}
