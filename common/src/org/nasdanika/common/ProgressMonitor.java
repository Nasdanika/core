package org.nasdanika.common;

/**
 * Progress monitor is used for reporting progress in 
 * the try with resources style:
 * 
 * ```
 * try (ProgressMonitor progressMonitor = ...) {
 *     while (...) {
 *         ...
 *         progressMonitor.worked(work, progressMessage);
 *     }
 * }
 * ```
 * 
 * @author Pavel
 *
 */
public interface ProgressMonitor extends AutoCloseable, Composeable<ProgressMonitor> {
	
	/**
	 * Progress status reported by worked(). For example, CANCEL status may be reported
	 * if the work was cancelled, or WARNING may be reported if, say the work has to perform re-tries due to poor network connectivity.
	 * @author Pavel
	 */
	enum Status {
		INFO,
		SUCCESS,
		WARNING,
		ERROR,
		CANCEL
	}
		
	/**
	 * Closes this monitor.
	 */
	@Override
	void close(); 
	
	/**
	 * Indicates that this monitor was cancelled and further operations shall not be performed.
	 * @return
	 */
	boolean isCancelled();
	
	/**
	 * Creates a sub-monitor of this monitor which will consume a given number of ticks from the receiver.
	 * @param Task name
	 * @param ticks Amount of work
	 * @param details optional additional information.
	 * @return
	 */
	ProgressMonitor split(String taskName, long ticks, Object... details);
	
	/**
	 * Reports progress.
	 * @param work Work installment - how much work was done since the last report, not the total work.
	 * @param progressMessage Progress message. 
	 */
	void worked(Status status, long work, String progressMessage, Object... details);
	
	/**
	 * Shortcut for ``worked(SUCCESS, work, progressMessage)``.
	 * @param work
	 * @param progressMessage
	 */
	default void worked(long work, String progressMessage, Object... details) {
		worked(Status.SUCCESS, work, progressMessage, details);
	}
	
//	/**
//	 * Resizes the remaining amount of work.
//	 * @param ticks
//	 */
//	void setWorkRemaining(int ticks);

	/**
	 * Composes two monitors. The resulting monitor reports isCancelled() as OR, composes splits, 
	 * and invokes other methods on both monitors.
	 */
	@Override
	default ProgressMonitor compose(ProgressMonitor other) {
		if (other == null) {
			return this;
		}
		return new ProgressMonitor() {
			
			@Override
			public void worked(Status status, long work, String progressMessage, Object... details) {
				ProgressMonitor.this.worked(status, work, progressMessage, details);
				other.worked(status, work, progressMessage, details);
			}
			
			@Override
			public ProgressMonitor split(String taskName, long ticks, Object... details) {
				return	ProgressMonitor.this.split(taskName, ticks, details).compose(other.split(taskName, ticks, details));
			}
			
			@Override
			public boolean isCancelled() {
				return ProgressMonitor.this.isCancelled() || other.isCancelled();
			}
			
			@Override
			public void close() {
				ProgressMonitor.this.close();
				other.close();
			}
			
		};
	}
		
}
