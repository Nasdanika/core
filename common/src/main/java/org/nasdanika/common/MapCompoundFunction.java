package org.nasdanika.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCompoundFunction<K,T,R> 
	extends MapCompoundExecutionParticipant<K, Function<? super T, ? extends R>> 
	implements Function<T,Map<K,R>>  {

	public MapCompoundFunction(String name) {
		super(name);
	}

	public MapCompoundFunction(String name, Map<K,Function<? super T, ? extends R>> functions) {
		super(name);
		functions.forEach(this::put);
	}

	@Override
	public Map<K,R> execute(T arg, ProgressMonitor progressMonitor) {
		progressMonitor.setWorkRemaining(size());
		Map<K,R> result = new LinkedHashMap<>();
		for (Entry<K, Function<? super T, ? extends R>> e: elements.entrySet()) {
			Function<? super T, ? extends R> value = e.getValue();
			if (value != null) {
				result.put(e.getKey(), value.splitAndExecute(arg, progressMonitor));			
			}
		}
		return result;
	}	

}
