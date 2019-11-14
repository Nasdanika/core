package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundFunction<K,T,R> extends MapCompoundExecutionParticipant<K,Function<T,R>> implements Function<T,Map<K,R>>  {

	public MapCompoundFunction(String name) {
		super(name);
	}

	public MapCompoundFunction(String name, Map<K,Function<T,R>> functions) {
		super(name);
		functions.forEach(this::put);
	}

	@Override
	public Map<K,R> execute(T arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		Map<K,R> result = new LinkedHashMap<>();
		for (Entry<K, Function<T, R>> e: elements.entrySet()) {
			result.put(e.getKey(), e.getValue().splitAndExecute(arg, progressMonitor));			
		}
		return result;
	}	

}
