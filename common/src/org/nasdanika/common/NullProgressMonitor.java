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

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public ProgressMonitor split(String taskName, long ticks, Object... details) {
		return new NullProgressMonitor();
	}

	@Override
	public void worked(Status status, long work, String progressMessage, Object... details) {
		// NOP
	}

}
