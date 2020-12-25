package org.nasdanika.common.persistence;

import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.SupplierFactory;

public class SupplierFactoryMapAttribute<K,V> extends AbstractFeatureDelegate<Feature<Map<K,V>>> implements SupplierFactoryFeature<Map<K,V>> {

	public SupplierFactoryMapAttribute(Feature<Map<K,V>> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<Map<K,V>> getValue() {
		MapCompoundSupplierFactory<K,V> ret = new MapCompoundSupplierFactory<>("Feature " + getKey());
		for (Entry<K, V> e: delegate.getValue().entrySet()) {
			ret.put(e.getKey(), SupplierFactory.<V>adapt(e.getValue(), "Feature " + getKey() + "[" + e.getKey() + "]"));
		}
		
		return ret;
	}

}
