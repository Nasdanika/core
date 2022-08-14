package org.nasdanika.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	
	private Map<K, List<? extends Marker>> entryMarkers = new HashMap<>();

	public void markEntry(K key, Marker marker) {
		markEntry(key, Collections.singletonList(marker));
	}
	
	public void markEntry(K key, List<? extends Marker> markers) {
		entryMarkers.put(key, markers);
	}

	public List<? extends Marker> getEntryMarkers(Object key) {
		return entryMarkers.get(key);
	}
	
	private List<? extends Marker> markers = new ArrayList<>();

	@Override
	public void mark(List<? extends Marker> markers) {
		this.markers = markers;		
	}

	@Override
	public List<? extends Marker> getMarkers() {
		return markers;
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
				markEntry(key, mm.getEntryMarkers(key));
			}
		}
	}
	
}
