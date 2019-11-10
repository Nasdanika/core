package org.nasdanika.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class MapCompoundExecutionParticipant<K, E extends ExecutionParticipant> extends CompoundExecutionParticipant<E> {

	protected MapCompoundExecutionParticipant(String name) {
		super(name);
	}

	protected Map<K,E> elements = new LinkedHashMap<>();

	@Override
	protected List<E> getElements() {
		return new ArrayList<E>(elements.values());
	}
	
	public void put(K key, E element) {
		elements.put(key, element);
	}

}
