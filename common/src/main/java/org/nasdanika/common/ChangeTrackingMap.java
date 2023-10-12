package org.nasdanika.common;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map which delegates to another map and tracks changes
 * @param <K>
 * @param <V>
 */
public class ChangeTrackingMap<K, V> extends AbstractMap<K, V> {
	
	private Map<K, V> target;
	private Set<Entry<K, V>> entrySet;

	public ChangeTrackingMap(Map<K,V> target) {
		this.target = target;
		this.entrySet = new AbstractSet<Map.Entry<K,V>>() {

			@Override
			public int size() {
				return target.size();
			}

			@Override
			public Iterator<Map.Entry<K,V>> iterator() {
				return new Iterator<Map.Entry<K,V>>() {
					Iterator<Entry<K, V>> targetIterator = target.entrySet().iterator();
					
					// Storing value to pass to the onRemove() method
					private Entry<K, V> value;

					@Override
					public boolean hasNext() {
						return targetIterator.hasNext();
					}

					@Override
					public Entry<K, V> next() {
						value = targetIterator.next();
						return value;
					}
					
					@Override
					public void remove() {
						targetIterator.remove();
					}
					
				};
			}
			
		};
	}
	
	@Override
	public V put(K key, V value) {
		V oldValue = target.put(key, value);
		onPut(key, oldValue, value);
		return oldValue;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	
	protected void onRemove(K key, V value) {
		
	}
	
	protected void onPut(K key, V oldValue, V newValue) {
		
	}

}
