package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Loads a map of references using loader.
 * @author Pavel
 *
 */
public class ReferenceMap<K,V> extends MapAttribute<K,V> {

	public ReferenceMap(Object key, 
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			Map<K,V> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);				
	}		
	
	@SuppressWarnings("unchecked")
	@Override
	protected V createValue(
			ObjectLoader loader, 
			K key, 
			Object value, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return (V) loader.load(value, base, resolver, progressMonitor);
	}

}
