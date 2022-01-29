package org.nasdanika.common.persistence;

import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Choice delegates to an object factory returned by the selector function which takes config as an argument.
 * @author Pavel
 *
 * @param <T>
 */
public class Choice<T> extends Attribute<T> {
	
	private Function<Object, ObjectFactory<T>> selector;
	
	public Choice(
			Object key, 
			Function<Object, ObjectFactory<T>> selector,
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.selector = selector;
	}
	
	/**
	 * This implementation casts config to the target type. It can be used where not special conversion is required, e.g. config is a String and feature type is String.
	 * @param loader
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@Override
	public T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return selector.apply(config).create(loader, config, base, progressMonitor, marker);
	}
	
}
