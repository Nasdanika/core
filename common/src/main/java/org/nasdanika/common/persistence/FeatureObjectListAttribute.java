package org.nasdanika.common.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * If config is a list loads each element creating elements using element factory and then loading them, otherwise creates a singleton in the same way as explained before.
 * @author Pavel
 *
 * @param <T>
 */
public class FeatureObjectListAttribute<T extends FeatureObject> extends ListAttribute<T> {

	private Supplier<T> elementFactory;

	public FeatureObjectListAttribute(
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
	@Override
	protected T createElement(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) { 
		T ret = elementFactory.get();
		ret.load(loader, element, base, progressMonitor, markers);
		return ret;
	}
	
}
