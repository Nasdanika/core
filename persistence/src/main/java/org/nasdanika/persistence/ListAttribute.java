package org.nasdanika.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

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
			boolean isConstructor, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
	}
	
	@Override
	public List<T> create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		if (config instanceof Collection) {
			List<T> ret = new ArrayList<>();
			int idx = 0;
			for (Object element: (Collection<?>) config) {
				ret.addAll(createElements(loader, element, base, resolver, Util.getMarkers((Collection<?>) config, idx++), progressMonitor));
			}
			return ret;
		}
		
		return createElements(loader, config, base, resolver, markers, progressMonitor);
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
	 */
	protected List<T> createElements(
			ObjectLoader loader, 
			Object element, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 
			ProgressMonitor progressMonitor) { 
		return Collections.singletonList(createElement(loader, element, base, resolver, markers, progressMonitor)); 
	}
	

	/**
	 * Creates element list.
	 * @param loader
	 * @param element
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T createElement(
			ObjectLoader loader, 
			Object element, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 			
			ProgressMonitor progressMonitor) { 
		return (T) element; 
	}
	
}
