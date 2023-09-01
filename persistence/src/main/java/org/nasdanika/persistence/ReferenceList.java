package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Loads a list of references using loader.
 * @author Pavel
 *
 */
public class ReferenceList<T> extends ListAttribute<T> {

	public ReferenceList(Object key, 
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T createElement(
			ObjectLoader loader, 
			Object element, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return (T) loader.load(element, base, resolver, progressMonitor);
	}

}
