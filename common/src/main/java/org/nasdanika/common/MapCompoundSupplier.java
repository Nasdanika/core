package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundSupplier<K,T> extends MapCompoundExecutionParticipant<K,Supplier<? extends T>> implements Supplier<Map<K,T>>  {

	public MapCompoundSupplier(String name) {
		super(name);
	}

	public MapCompoundSupplier(String name, Map<K,Supplier<? extends T>> suppliers) {
		super(name);
		suppliers.forEach(this::put);
	}

	@Override
	public Map<K,T> execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		Map<K,T> result = new LinkedHashMap<>();
		for (Entry<K, Supplier<? extends T>> e: elements.entrySet()) {
			Supplier<? extends T> value = e.getValue();
			if (value != null) {
				result.put(e.getKey(), value.splitAndExecute(progressMonitor));			
			}
		}
		return result;
	}	

}
