package org.nasdanika.common.persistence;

import java.util.List;

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
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T createElement(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return (T) loader.load(element, base, progressMonitor);
	}

}
