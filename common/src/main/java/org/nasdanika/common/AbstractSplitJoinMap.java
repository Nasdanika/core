package org.nasdanika.common;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractSplitJoinMap<S,C,K,V> extends AbstractMap<K, V> {
	
	/**
	 * @return State to be split into chunks.
	 */
	protected abstract S getState();
	
	/**
	 * Sets state composed from chunks created from elements.
	 * @param state
	 */
	protected abstract void setState(S state);
	
	/**
	 * Splits state into an array of chunks
	 * @param state
	 * @return
	 */
	protected abstract List<C> split(S state);
	
	/**
	 * Joins an array of chunks into a state.
	 * @param chunks
	 * @return
	 */
	protected abstract S join(List<C> chunks);
	
	/**
	 * Loads a chunk - converts it to an unmodifiable entry which does not have to implement setValue()
	 * as it is not used anyway.
	 * @param chunk
	 * @return
	 */
	protected abstract Entry<K,V> load(C chunk);
	
	/**
	 * Stores an element - converts it into a chunk  
	 * @param element
	 * @return
	 */
	protected abstract C store(Entry<K,V> element);
	
	private AbstractSplitJoinSet<S, C, Entry<K,V>> entrySet = new AbstractSplitJoinSet<S, C, Entry<K,V>>() {

		@Override
		protected S getState() {
			return AbstractSplitJoinMap.this.getState();
		}

		@Override
		protected void setState(S state) {
			AbstractSplitJoinMap.this.setState(state);
		}

		@Override
		protected List<C> split(S state) {
			return AbstractSplitJoinMap.this.split(state);
		}

		@Override
		protected S join(List<C> chunks) {
			return AbstractSplitJoinMap.this.join(chunks);
		}

		@Override
		protected Entry<K, V> load(C chunk) {
			Entry<K, V> valueEntry = AbstractSplitJoinMap.this.load(chunk);			
			return new Entry<K,V>() {
				V value = valueEntry.getValue();

				@Override
				public K getKey() {
					return valueEntry.getKey();
				}

				@Override
				public V getValue() {
					return value;
				}

				@Override
				public V setValue(V value) {
					if (Objects.equals(this.value, value)) {
						return value;
					}
					V prevValue = this.value;
					this.value = value;
					List<C> chunks = new ArrayList<>();
					for (C chunk: split(getState())) {
						Entry<K, V> e = load(chunk);
						if (Objects.equals(e.getKey(), valueEntry.getKey())) {
							chunks.add(store(this));
						} else {
							chunks.add(chunk);
						}
					}
					setState(join(chunks));
					return prevValue;
				}
				
			};
		}

		@Override
		protected C store(Entry<K, V> element) {
			return AbstractSplitJoinMap.this.store(element);
		}
		
	};

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	
	@Override
	public V put(K key, V value) {
		Set<Entry<K, V>> entrySet = entrySet();
		for (Entry<K,V> e: entrySet) {
			if (Objects.equals(key, e.getKey())) {
				V prev = e.getValue();
				e.setValue(value);
				return prev;
			}
		}
		entrySet.add(new Entry<K,V>() {

			@Override
			public K getKey() {
				return key;
			}

			@Override
			public V getValue() {
				return value;
			}

			@Override
			public V setValue(V value) {
				throw new UnsupportedOperationException();
			}
			
		});
		return null;
	}

}
