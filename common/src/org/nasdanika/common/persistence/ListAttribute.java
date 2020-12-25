package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * If config is a list loads each element using createElement(), otherwise creates a singleton loaded with createElement()
 * @author Pavel
 *
 * @param <T>
 */
public class ListAttribute<T> extends Attribute<List<T>> {

	public ListAttribute(Object key, boolean required, List<T> defaultValue, Object... exclusiveWith) {
		super(key, false, required, defaultValue, null, exclusiveWith);
	}
	
	@Override
	public List<T> create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
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
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception { 
		return (T) element; 
	}
	
}
