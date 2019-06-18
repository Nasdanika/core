package org.nasdanika.common;

/**
 * NOP implementation which tracks cancellation status and returns a new {@link NullProgressMonitor} from ``split()``.
 * @author Pavel
 *
 */
public class NullProgressMonitor implements ProgressMonitor {
	
	private volatile boolean cancelled;

	public NullProgressMonitor() {}

	@Override
	public void close() {}

	@Override
	public void cancel() {
		cancelled = true;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public ProgressMonitor split(String taskName, int ticks) {
		return new NullProgressMonitor();
	}

	@Override
	public void worked(int work, String progressMessage) {
		// NOP
	}

}
