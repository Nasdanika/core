package org.nasdanika.ncore.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.common.util.URI;

/**
 * A map which takes specifics of {@link SemanticIdentity} equals into account and also provides get methods by UUID and URI
 * @author Pavel
 *
 * @param <V>
 */
public class SemanticMap<K extends SemanticIdentity, V> extends AbstractMap<K,V> {
	
	private Collection<Entry<K, V>> data = new ArrayList<>();
	
	private Set<Entry<K, V>> entrySet = new AbstractSet<Entry<K, V>>() {

		@Override
		public int size() {
			return data.size();
		}

		@Override
		public Iterator<Entry<K, V>> iterator() {
			Iterator<Entry<K, V>> dataIterator = data.iterator();
			return new Iterator<Entry<K,V>>() {

				@Override
				public boolean hasNext() {
					return dataIterator.hasNext();
				}

				@Override
				public Entry<K, V> next() {
					return dataIterator.next();
				}
				
				public void remove() {
					dataIterator.remove();
				};
				
			};
		}

		@Override
		public boolean add(Entry<K, V> e) {
			for (Entry<K, V> de: data) {
				if (Objects.equals(de.getKey(), e.getKey())) {
					return false;
				}
			}
			return data.add(e);
		}
		
	};

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	
	@Override
	public V put(K key, V value) {
		for (Entry<K, V> de: data) {
			if (Objects.equals(de.getKey(), key)) {
				return de.setValue(value);
			}
		}
		
		data.add(new Entry<K, V>() {
			
			V entryValue = value;

			@Override
			public K getKey() {
				return key;
			}

			@Override
			public V getValue() {
				return entryValue;
			}

			@Override
			public V setValue(V newValue) {
				V ret = entryValue;
				entryValue = newValue;
				return ret;
			}
		});

		return null;
	}
	
	/**
	 * Find entry by URI
	 * @param key
	 * @return
	 */
	public Entry<K,V> find(URI uri) {
		if (uri != null) {
			for (Entry<K, V> e: entrySet()) {
				if (e.getKey().getIdentifiers().contains(uri)) {
					return e;
				}
			}
		}
		return null;		
	}

}
