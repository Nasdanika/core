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
	 * Closes this monitor.
	 */
	@Override
	void close(); 

	/**
	 * Sets this monitor state to cancelled. Clients shall periodically 
	 * call ``isCancelled()`` and cancel operations if it returns true.  
	 */
	void cancel();
	
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
	void worked(long work, String progressMessage);
	
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
			public void worked(long work, String progressMessage) {
				ProgressMonitor.this.worked(work, progressMessage);
				other.worked(work, progressMessage);
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
			
			@Override
			public void cancel() {
				ProgressMonitor.this.cancel();
				other.cancel();
			}
			
//			@Override
//			public void setWorkRemaining(int ticks) {
//				ProgressMonitor.this.setWorkRemaining(ticks);
//				other.setWorkRemaining(ticks);
//			}
		};
	}
		
}
