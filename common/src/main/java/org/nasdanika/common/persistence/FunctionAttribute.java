package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.function.Function;

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
			Function<Object,T> function, 
			boolean required, 
			T defaultValue,
			String description,
			Object[] exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.function = function;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return function.apply(config);
	}

}
