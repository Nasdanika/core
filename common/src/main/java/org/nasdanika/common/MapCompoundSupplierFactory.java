package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundSupplierFactory<K,T> implements SupplierFactory<Map<K,T>> {

	private String name;
	private Map<K,SupplierFactory<? extends T>> elements = new LinkedHashMap<>();

	public MapCompoundSupplierFactory(String name, Map<K,SupplierFactory<? extends T>> elements) {
		this.name = name;
		this.elements.putAll(elements);
	}
	
	public MapCompoundSupplierFactory(String name) {
		this.name = name;
	}

	@Override
	public Supplier<Map<K,T>> create(Context context) throws Exception {
		MapCompoundSupplier<K,T> ret = new MapCompoundSupplier<K,T>(name);
		for (Entry<K, SupplierFactory<? extends T>> e: elements.entrySet()) {
			SupplierFactory<? extends T> value = e.getValue();
			if (value != null) {
				ret.put(e.getKey(), value.create(context));
			}
		}
		return ret;
	}
	
	public void put(K key, SupplierFactory<? extends T> element) {
		elements.put(key, element);
	}	
	
	public void putAll(Map<? extends K, ? extends SupplierFactory<? extends T>> elements) {
		elements.entrySet().forEach(e -> put(e.getKey(), e.getValue()));
	}

}
