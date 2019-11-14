package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundFunctionFactory<K,T,R> implements FunctionFactory<T,Map<K,R>> {

	private String name;
	private Map<K,FunctionFactory<T,R>> elements = new LinkedHashMap<>();

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
		for (Entry<K, FunctionFactory<T, R>> e: elements.entrySet()) {
			ret.put(e.getKey(), e.getValue().create(context));
		}
		return ret;
	}
	
	public void put(K key, FunctionFactory<T,R> element) {
		elements.put(key, element);
	}	

}
