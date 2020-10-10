package org.nasdanika.common;

/**
 * Delegates to the target {@link ProgressMonitor}. 
 * Subclass and override methods as needed. 
 * @author Pavel
 *
 */
public class FilterProgressMonitor implements ProgressMonitor {
	
	protected ProgressMonitor target;

	public FilterProgressMonitor(ProgressMonitor target) {
		this.target = target;
	}

	@Override
	public void close() {}

	@Override
	public boolean isCancelled() {
		return target.isCancelled();
	}

	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		return target.split(taskName, size, data); 
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		target.worked(status, work, progressMessage, data);
	}

	@Override
	public ProgressMonitor setWorkRemaining(double size) {
		target.setWorkRemaining(size);
		return this;
	}

}
