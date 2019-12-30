package org.nasdanika.common;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public class ElementIdentityMapCompoundFunction<T,R> extends ElementIdentityMapCompoundExecutionParticipant<Function<T,R>,R> implements Function<T,java.util.function.Function<Function<T,R>,R>>  {

	public ElementIdentityMapCompoundFunction(String name) {
		super(name);
	}

	public ElementIdentityMapCompoundFunction(String name, Collection<Function<T,R>> functions) {
		super(name);
		functions.forEach(this::put);
	}

	@Override
	public java.util.function.Function<Function<T, R>, R> execute(T arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		Map<Function<T,R>,R> result = new IdentityHashMap<>();
		for (Function<T,R> e: elements) {
			result.put(e, e.splitAndExecute(arg, progressMonitor));			
		}
		return result::get; 
	}	

}
