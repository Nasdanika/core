package org.nasdanika.common;

public class FilterFunction<T,R> extends FilterExecutionParticipant<Function<T,R>> implements Function<T,R> { 

	public FilterFunction(Function<T,R> target) {
		super(target);
	}

	@Override
	public R execute(T arg, ProgressMonitor progressMonitor) {
		return target.execute(arg, progressMonitor);
	}

}
