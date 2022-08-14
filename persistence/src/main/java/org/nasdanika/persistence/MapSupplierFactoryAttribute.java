package org.nasdanika.persistence;

import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Context;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

public class MapSupplierFactoryAttribute<K,V,U> extends AbstractFeatureDelegate<Feature<Map<K,U>>> implements SupplierFactoryFeature<Map<K,V>> {

	private boolean interpolate;

	public MapSupplierFactoryAttribute(Feature<Map<K,U>> delegate, boolean interpolate) {
		super(delegate);
		this.interpolate = interpolate;
	}

	@Override
	public SupplierFactory<Map<K,V>> getValue() {
		MapCompoundSupplierFactory<K,V> ret = new MapCompoundSupplierFactory<>("Feature " + getKey());
		if (delegate.getValue() != null) {
			for (Entry<K, U> e: delegate.getValue().entrySet()) {
				if (e.getValue() instanceof String && interpolate) {
					ret.put(e.getKey(), new SupplierFactory<V>() {

						@Override
						public Supplier<V> create(Context context) {
							Object ie = context.interpolate((String) e.getValue());
							return SupplierFactory.<V>adapt(ie, "Interpolated feature " + getKey() + "[" + e.getKey() + "]").create(context);							
						}
						
					});					
				} else {
					ret.put(e.getKey(), SupplierFactory.<V>adapt(e.getValue(), "Feature " + getKey() + "[" + e.getKey() + "]"));
				}
			}
		}		
		
		return ret;
	}

}
