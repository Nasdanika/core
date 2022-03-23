package org.nasdanika.common;

/**
 * NOP implementation which tracks cancellation status and returns a new {@link NullProgressMonitor} from ``split()``.
 * @author Pavel
 *
 */
public class NullProgressMonitor implements ProgressMonitor {
	
	public NullProgressMonitor() {}

	@Override
	public void close() {}
	
	private boolean cancelled;

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		return this;
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		if (status == Status.CANCEL) {
			cancelled = true;
		}
	}

	@Override
	public ProgressMonitor setWorkRemaining(double size) {
		// NOP	
		return this;
	}

}
