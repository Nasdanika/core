package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

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
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		// Strings are references. Always with the type?
		if (element instanceof String) {
			String ref = (String) element;
			URL refURL = new URL(base, ref);
			if (ref.endsWith(".json")) {
				return (T) loader.loadJsonObject(refURL, progressMonitor);
			}
			return (T) loader.loadYaml(refURL, progressMonitor);
		}
		return (T) loader.load(element, base, progressMonitor);
	}

}
