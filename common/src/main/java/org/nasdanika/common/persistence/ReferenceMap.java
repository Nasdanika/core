package org.nasdanika.common.persistence;

import java.util.List;
import java.util.Map;

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
			boolean required, 
			Map<K,V> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);				
	}		
	
	@SuppressWarnings("unchecked")
	@Override
	protected V createValue(ObjectLoader loader, K key, Object value, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		return (V) loader.load(value, base, progressMonitor);
	}

}
