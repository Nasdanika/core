package org.nasdanika.common;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Consumes a map and executes each element passing to it a matching input. 
 * @author Pavel
 *
 * @param <K>
 * @param <T>
 */
public class MapCompoundConsumer<K,T> extends MapCompoundExecutionParticipant<K,Consumer<? super T>> implements Consumer<Map<K,T>>  {

	public MapCompoundConsumer(String name) {
		super(name);
	}

	public MapCompoundConsumer(String name, Map<K,Consumer<? super T>> suppliers) {
		super(name);
		suppliers.forEach(this::put);
	}

	@Override
	public void execute(Map<K,T> input, ProgressMonitor progressMonitor) {
		progressMonitor.setWorkRemaining(size());
		for (Entry<K, Consumer<? super T>> e: elements.entrySet()) {
			Consumer<? super T> value = e.getValue();
			if (value != null) {
				value.splitAndExecute(input.get(e.getKey()), progressMonitor);			
			}
		}
	}

}
