package org.nasdanika.common;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public class ElementIdentityMapCompoundSupplier<T> extends ElementIdentityMapCompoundExecutionParticipant<Supplier<T>,T> implements Supplier<java.util.function.Function<Supplier<T>,T>>  {

	public ElementIdentityMapCompoundSupplier(String name) {
		super(name);
	}

	public ElementIdentityMapCompoundSupplier(String name, Collection<Supplier<T>> suppliers) {
		super(name);
		suppliers.forEach(this::put);
	}

	@Override
	public java.util.function.Function<Supplier<T>,T> execute(ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		Map<Supplier<T>,T> result = new IdentityHashMap<>();
		for (Supplier<T> e: elements) {
			result.put(e, e.splitAndExecute(progressMonitor));			
		}
		return result::get; 
	}	

}
