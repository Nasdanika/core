package org.nasdanika.common._legacy;

import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

public class FilterWork<T> extends FilterCommand<T> implements Supplier<T> {

	public FilterWork(Supplier<T> target) {
		super(target);
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		((Supplier<T>) target).commit(progressMonitor);
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		return ((Supplier<T>) target).rollback(progressMonitor);
	}

	@Override
	public double size() {
		return ((Supplier<T>) target).size();
	}

	@Override
	public String name() {
		return ((Supplier<T>) target).name();
	}

	@Override
	public <R> Supplier<R> adapt(Function<T, R> adapter) {
		return ((Supplier<T>) target).adapt(adapter);
	}
	
}
