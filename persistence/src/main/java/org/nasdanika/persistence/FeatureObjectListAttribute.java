package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
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
			boolean isConstructor, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.elementFactory = elementFactory;
	}
	
	@Override
	protected T createElement(
			ObjectLoader loader, 
			Object element, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		T ret = elementFactory.get();
		ret.load(loader, element, base, resolver, markers, progressMonitor);
		return ret;
	}
	
}
