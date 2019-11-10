package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public class ListCompoundFunction<T,R> extends ListCompoundExecutionParticipant<Function<T,R>> implements Function<T,List<R>>  {

	public ListCompoundFunction(String name) {
		super(name);
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
