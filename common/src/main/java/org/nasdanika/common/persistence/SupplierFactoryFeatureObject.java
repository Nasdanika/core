package org.nasdanika.common.persistence;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * Feature object implementing {@link SupplierFactory}. The factory result is constructed from a {@link Map} of feature
 * keys to feature values. If feature values are SupplierFactories they get executed to produce actual values.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class SupplierFactoryFeatureObject<T> extends FeatureObject implements SupplierFactory<T> {

	@Override
	public Supplier<T> create(Context context) {
		// TODO - handling of prototype here - prototype supplier factory, bi-supplier
		MapCompoundSupplierFactory<Object, Object> featureMapFactory = new MapCompoundSupplierFactory<>("Feature map factory");
		for (Feature<?> feature: features) {			
			@SuppressWarnings("unchecked")
			SupplierFactory<Object> fsf = feature instanceof SupplierFactoryFeature ? ((SupplierFactoryFeature<Object>) feature).getValue() : SupplierFactory.from(feature.getValue(), "Feature " + feature.getKey());
			featureMapFactory.put(feature.getKey(), fsf);
		}
		
		return featureMapFactory.then(this::createResultFunction).create(context);
	}

	/**
	 * @param context
	 * @return {@link Function} which would create a result object from feature values.
	 */
	protected abstract Function<Map<Object, Object>, T> createResultFunction(Context context);

}
