package org.nasdanika.common;

public class FilterConsumer<T> extends FilterExecutionParticipant<Consumer<T>> implements Consumer<T> { 

	public FilterConsumer(Consumer<T> target) {
		super(target);
	}

	@Override
	public void execute(T arg, ProgressMonitor progressMonitor) throws Exception {
		target.execute(arg, progressMonitor);
	}

}
