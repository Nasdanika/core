package org.nasdanika.common.persistence;

import java.util.List;
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
			Function<Object,T> function, 
			boolean required, 
			T defaultValue,
			String description,
			Object[] exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.function = function;
	}
	
	@Override
	public T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception {
		return function.apply(config);
	}

}
