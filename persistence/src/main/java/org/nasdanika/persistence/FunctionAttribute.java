package org.nasdanika.persistence;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Applies a function to the config value to produce feature value.
 * @author Pavel
 *
 * @param <T>
 */
public class FunctionAttribute<T> extends Attribute<T> {

	private Function<Object,T> function;

	public FunctionAttribute(
			Object key,
			boolean isDefault,
			boolean isConstructor, 
			Function<Object,T> function, 
			boolean required, 
			T defaultValue,
			String description,
			Object[] exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.function = function;
	}
	
	@Override
	public T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return function.apply(config);
	}

}
