package org.nasdanika.common.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * If config is a list loads each element using createElement(), otherwise creates a singleton loaded with createElement()
 * @author Pavel
 *
 * @param <T>
 */
public class ListAttribute<T> extends Attribute<List<T>> {

	public ListAttribute(Object key, 
			boolean isDefault, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
	}
	
	@Override
	public List<T> create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
		if (config instanceof Collection) {
			List<T> ret = new ArrayList<>();
			int idx = 0;
			for (Object element: (Collection<?>) config) {
				ret.add(createElement(loader, element, base, progressMonitor, Util.getMarker((Collection<?>) config, idx++)));
			}
			return ret;
		}
		
		return Collections.singletonList(createElement(loader, config, base, progressMonitor, marker));
	}

	/**
	 * Creates element list.
	 * @param loader
	 * @param element
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected T createElement(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, Marker marker)	throws Exception { 
		return (T) element; 
	}
	
}
