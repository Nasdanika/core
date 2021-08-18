package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListCompoundFunction<T,R> extends ListCompoundExecutionParticipant<Function<? super T, ? extends R>> implements Function<T,List<R>>  {

	@SafeVarargs
	public ListCompoundFunction(String name, Function<? super T, ? extends R>... functions) {
		this(name, Arrays.asList(functions));
	}
	
	public ListCompoundFunction(String name, Collection<Function<? super T, ? extends R>> functions) {
		super(name);
		for (Function<? super T, ? extends R> function: functions) {
			add(function);
		}
	}	

	@Override
	public List<R> execute(T arg, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.setWorkRemaining(size());
		List<R> result = new ArrayList<>();
		for (Function<? super T, ? extends R> e: getElements()) {
			result.add(e == null ? null : e.splitAndExecute(arg, progressMonitor));			
		}
		return result;
	}	

}
