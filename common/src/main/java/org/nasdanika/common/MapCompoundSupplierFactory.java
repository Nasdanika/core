package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundSupplierFactory<K,T> implements SupplierFactory<Map<K,T>> {

	private String name;
	private Map<K,SupplierFactory<T>> elements = new LinkedHashMap<>();

	public MapCompoundSupplierFactory(String name, Map<K,SupplierFactory<T>> elements) {
		this.name = name;
		this.elements.putAll(elements);
	}
	
	public MapCompoundSupplierFactory(String name) {
		this.name = name;
	}

	@Override
	public Supplier<Map<K,T>> create(Context context) throws Exception {
		MapCompoundSupplier<K,T> ret = new MapCompoundSupplier<K,T>(name);
		for (Entry<K, SupplierFactory<T>> e: elements.entrySet()) {
			ret.put(e.getKey(), e.getValue().create(context));
		}
		return ret;
	}
	
	public void put(K key, SupplierFactory<T> element) {
		elements.put(key, element);
	}	

}
