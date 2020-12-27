package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.Map;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;

/**
 * If config is a list loads each element creating elements using element factory and then loading them, otherwise creates a singleton in the same way as explained before.
 * @author Pavel
 *
 * @param <T>
 */
public class FeatureObjectMapAttribute<T extends FeatureObject> extends MapAttribute<T> {


	private Function<Object, T> valueFactory;

	/**
	 * 
	 * @param key
	 * @param valueFactory Factory of values which takes key as an argument.
	 * @param isDefault
	 * @param required
	 * @param defaultValue
	 * @param description
	 * @param exclusiveWith
	 */
	public FeatureObjectMapAttribute(
			Object key, 
			Function<Object,T> valueFactory,
			boolean isDefault, 
			boolean required, 
			Map<?,T> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.valueFactory = valueFactory;
	}

	/**
	 * Creates map value.
	 * @param loader
	 * @param element
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 * @throws Exception
	 */
	@Override
	protected T createValue(ObjectLoader loader, Object key, Object value, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception { 
		T ret = valueFactory.apply(key);
		ret.load(loader, value, base, progressMonitor, marker);
		return ret;
	}
	
}
