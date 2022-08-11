package org.nasdanika.common;

public class FilterSupplier<T> extends FilterExecutionParticipant<Supplier<T>> implements Supplier<T> { 

	public FilterSupplier(Supplier<T> target) {
		super(target);
	}

	@Override
	public T execute(ProgressMonitor progressMonitor) {
		return target.execute(progressMonitor);
	}

}
