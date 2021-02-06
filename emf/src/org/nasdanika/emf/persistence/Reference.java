package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.List;
import java.util.function.Supplier;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * If config is a list loads each element creating elements using element factory and then loading them, otherwise creates a singleton in the same way as explained before.
 * @author Pavel
 *
 * @param <T>
 */
public class Reference extends ListAttribute<EObjectSupplierFactory> {

	private Supplier<T> elementFactory;

	public Reference(
			Object key, 
			Supplier<T> elementFactory,
			boolean isDefault, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.elementFactory = elementFactory;
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
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception { 
		T ret = elementFactory.get();
		ret.load(loader, element, base, progressMonitor, marker);
		return ret;
	}
	
}
