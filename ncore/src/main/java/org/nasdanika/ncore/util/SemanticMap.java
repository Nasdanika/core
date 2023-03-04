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
public class SemanticMap<V> extends AbstractMap<SemanticIdentity,V> {
	
	private Collection<Entry<SemanticIdentity, V>> data = new ArrayList<>();
	
	private Set<Entry<SemanticIdentity, V>> entrySet = new AbstractSet<Entry<SemanticIdentity, V>>() {

		@Override
		public int size() {
			return data.size();
		}

		@Override
		public Iterator<Entry<SemanticIdentity, V>> iterator() {
			Iterator<Entry<SemanticIdentity, V>> dataIterator = data.iterator();
			return new Iterator<Entry<SemanticIdentity,V>>() {

				@Override
				public boolean hasNext() {
					return dataIterator.hasNext();
				}

				@Override
				public Entry<SemanticIdentity, V> next() {
					return dataIterator.next();
				}
				
				public void remove() {
					dataIterator.remove();
				};
				
			};
		}

		@Override
		public boolean add(Entry<SemanticIdentity, V> e) {
			for (Entry<SemanticIdentity, V> de: data) {
				if (Objects.equals(de.getKey(), e.getKey())) {
					return false;
				}
			}
			return data.add(e);
		}
		
	};

	@Override
	public Set<Entry<SemanticIdentity, V>> entrySet() {
		return entrySet;
	}
	
	@Override
	public V put(SemanticIdentity key, V value) {
		for (Entry<SemanticIdentity, V> de: data) {
			if (Objects.equals(de.getKey(), key)) {
				return de.setValue(value);
			}
		}
		
		data.add(new Entry<SemanticIdentity, V>() {
			
			V entryValue = value;

			@Override
			public SemanticIdentity getKey() {
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
	public Entry<SemanticIdentity,V> find(URI uri) {
		if (uri != null) {
			for (Entry<SemanticIdentity, V> e: entrySet()) {
				if (e.getKey().getIdentifiers().contains(uri)) {
					return e;
				}
			}
		}
		return null;		
	}

}
