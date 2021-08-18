package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundFunctionFactory<K,T,R> implements FunctionFactory<T,Map<K,R>> {

	private String name;
	private Map<K,FunctionFactory<? super T, ? extends R>> elements = new LinkedHashMap<>();

	public MapCompoundFunctionFactory(String name, Map<K,FunctionFactory<T,R>> elements) {
		this.name = name;
		this.elements.putAll(elements);
	}
	
	public MapCompoundFunctionFactory(String name) {
		this.name = name;
	}

	@Override
	public Function<T,Map<K,R>> create(Context context) throws Exception {
		MapCompoundFunction<K, T, R> ret = new MapCompoundFunction<K,T,R>(name);
		for (Entry<K, FunctionFactory<? super T, ? extends R>> e: elements.entrySet()) {
			FunctionFactory<? super T, ? extends R> value = e.getValue();
			if (value != null) {
				ret.put(e.getKey(), value.create(context));
			}
		}
		return ret;
	}
	
	public void put(K key, FunctionFactory<? super T, ? extends R> element) {
		elements.put(key, element);
	}	
	
	public void putAll(Map<? extends K, ? extends FunctionFactory<? super T, ? extends R>> elements) {
		elements.entrySet().forEach(e -> put(e.getKey(), e.getValue()));
	}

}
