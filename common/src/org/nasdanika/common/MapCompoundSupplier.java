package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundSupplier<K,T> extends MapCompoundExecutionParticipant<K,Supplier<T>> implements Supplier<Map<K,T>>  {

	public MapCompoundSupplier(String name) {
		super(name);
	}

	@Override
	public Map<K,T> execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		Map<K,T> result = new LinkedHashMap<>();
		for (Entry<K, Supplier<T>> e: elements.entrySet()) {
			result.put(e.getKey(), e.getValue().splitAndExecute(progressMonitor));			
		}
		return result;
	}	

}
