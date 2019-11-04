package org.nasdanika.common;

import java.util.function.Function;

public class FilterWork<T> extends FilterCommand<T> implements Work<T> {

	public FilterWork(Work<T> target) {
		super(target);
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		((Work<T>) target).commit(progressMonitor);
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		return ((Work<T>) target).rollback(progressMonitor);
	}

	@Override
	public double size() {
		return ((Work<T>) target).size();
	}

	@Override
	public String getName() {
		return ((Work<T>) target).getName();
	}

	@Override
	public <R> Work<R> adapt(Function<T, R> adapter) {
		return ((Work<T>) target).adapt(adapter);
	}
	
}
