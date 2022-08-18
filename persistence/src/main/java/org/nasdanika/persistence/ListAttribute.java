package org.nasdanika.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

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
	public List<T> create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		if (config instanceof Collection) {
			List<T> ret = new ArrayList<>();
			int idx = 0;
			for (Object element: (Collection<?>) config) {
				ret.addAll(createElements(loader, element, base, progressMonitor, Util.getMarkers((Collection<?>) config, idx++)));
			}
			return ret;
		}
		
		return createElements(loader, config, base, progressMonitor, markers);
	}
	
	/**
	 * Creates list elements from a config element. May create zero or more elements. 
	 * This implementation delegates to createElement(), i.e. creates a single element. 
	 * @param loader
	 * @param element
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	protected List<T> createElements(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) { 
		return Collections.singletonList(createElement(loader, element, base, progressMonitor, markers)); 
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
	protected T createElement(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) { 
		return (T) element; 
	}
	
}