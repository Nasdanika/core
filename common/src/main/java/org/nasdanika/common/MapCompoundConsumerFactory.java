package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Pavel
 *
 * @param <K>
 * @param <T>
 */
public class MapCompoundConsumerFactory<K,T> implements ConsumerFactory<Map<K,T>> {

	private String name;
	private Map<K,ConsumerFactory<? super T>> elements = new LinkedHashMap<>();

	public MapCompoundConsumerFactory(String name, Map<K,ConsumerFactory<? super T>> elements) {
		this.name = name;
		this.elements.putAll(elements);
	}
	
	public MapCompoundConsumerFactory(String name) {
		this.name = name;
	}

	@Override
	public Consumer<Map<K,T>> create(Context context) throws Exception {
		MapCompoundConsumer<K,T> ret = new MapCompoundConsumer<K,T>(name);
		for (Entry<K, ConsumerFactory<? super T>> e: elements.entrySet()) {
			ConsumerFactory<? super T> value = e.getValue();
			if (value != null) {
				ret.put(e.getKey(), value.create(context));
			}
		}
		return ret;
	}
	
	public void put(K key, ConsumerFactory<? super T> element) {
		elements.put(key, element);
	}	
	
	public void putAll(Map<? extends K, ? extends ConsumerFactory<? super T>> elements) {
		elements.entrySet().forEach(e -> put(e.getKey(), e.getValue()));
	}

}
