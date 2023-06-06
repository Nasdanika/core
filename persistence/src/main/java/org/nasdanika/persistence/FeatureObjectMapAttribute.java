package org.nasdanika.persistence;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * If config is a map loads each entry value creating elements using value factory and then loading them.
 * @author Pavel
 *
 * @param <V>
 */
public class FeatureObjectMapAttribute<K, V extends FeatureObject> extends MapAttribute<K,V> {


	private Function<K, V> valueFactory;

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
			Function<K,V> valueFactory,
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			Map<K,V> defaultValue, 
			String description, 
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
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
	 */
	@Override
	protected V createValue(ObjectLoader loader, K key, Object value, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) { 
		V ret = valueFactory.apply(key);
		ret.load(loader, value, base, progressMonitor, markers);
		return ret;
	}
	
}
