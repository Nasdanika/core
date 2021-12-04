package org.nasdanika.common.persistence;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Linked hash map which provides source location information for keys.
 * @author Pavel Vlasov
 *
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("serial")
public class MarkedLinkedHashMap<K, V> extends LinkedHashMap<K, V> implements Marked, Markable {
	
	@Override
		public V get(Object key) {
			// TODO Auto-generated method stub
			return super.get(key);
		}
	
	private Map<K, Marker> markers = new HashMap<>();
	
	public void mark(K key, Marker marker) {
		markers.put(key, marker);
	}

	public Marker getMarker(Object key) {
		return markers.get(key);
	}
	
	private Marker marker;

	@Override
	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public Marker getMarker() {
		return marker;
	}
	
	/**
	 * Also carries over markers
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		super.putAll(m);
		if (m instanceof MarkedLinkedHashMap) {
			MarkedLinkedHashMap<? extends K,?> mm = (MarkedLinkedHashMap<? extends K,?>) m;
			for (K key: mm.keySet()) {
				mark(key, mm.getMarker(key));
			}
		}
	}
	
}
