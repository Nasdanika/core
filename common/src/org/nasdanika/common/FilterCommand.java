package org.nasdanika.common;

import java.util.function.Function;

public class FilterCommand<T> implements Command<T> {

	protected Command<T> target;

	public FilterCommand(Command<T> target) {
		this.target = target;
	}

	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		return target.diagnose(progressMonitor);
	}

	@Override
	public T execute(ProgressMonitor progressMonitor) throws Exception {
		return target.execute(progressMonitor);
	}

	@Override
	public <R> Command<R> adapt(Function<T, R> adapter) {
		return target.adapt(adapter);
	}

	@Override
	public void close() throws Exception {
		target.close();
	}

}
