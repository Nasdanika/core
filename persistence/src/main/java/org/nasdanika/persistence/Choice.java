package org.nasdanika.persistence;

import java.util.Collection;
import java.util.function.BiConsumer;
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
			boolean isConstructor, 
			boolean required, 
			T defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.selector = selector;
	}
	
	@Override
	public T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return selector.apply(config).create(loader, config, base, resolver, markers, progressMonitor);
	}
	
}
