package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public class ListCompoundFunction<T,R> extends ListCompoundExecutionParticipant<Function<T,R>> implements Function<T,List<R>>  {

	@SafeVarargs
	public ListCompoundFunction(String name, Function<T,R>... functions) {
		super(name);
		for (Function<T, R> function: functions) {
			add(function);
		}
	}

	@Override
	public List<R> execute(T arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		List<R> result = new ArrayList<>();
		for (Function<T,R> e: getElements()) {
			result.add(e.splitAndExecute(arg, progressMonitor));			
		}
		return result;
	}	

}
