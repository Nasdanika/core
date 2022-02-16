package org.nasdanika.common;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class MapCompoundExecutionParticipant<K, E extends ExecutionParticipant> extends CompoundExecutionParticipant<E> {

	protected MapCompoundExecutionParticipant(String name) {
		super(name, false);
	}

	protected Map<K,E> elements = new LinkedHashMap<>();
	
	private Double size;
	
	@Override
	public double size() {
		if (size == null) { 
			size = super.size();
		}
		return size;
	}

	@Override
	protected Collection<E> getElements() {
		return elements.values();
	}
	
	public void put(K key, E element) {
		elements.put(key, element);
		size = null;
	}
	
}
