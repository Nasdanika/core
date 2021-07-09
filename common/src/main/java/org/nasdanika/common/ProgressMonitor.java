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
	ProgressMonitor split(String taskName, double size, Object... data);
	
	/**
	 * Reports progress.
	 * @param work Supplier installment - how much work was done since the last report, not the total work.
	 * @param progressMessage Progress message. 
	 */
	void worked(Status status, double work, String progressMessage, Object... data);
	
	/**
	 * Shortcut for ``worked(SUCCESS, work, progressMessage)``.
	 * @param work
	 * @param progressMessage
	 */
	default void worked(double work, String progressMessage, Object... data) {
		worked(Status.SUCCESS, work, progressMessage, data);
	}
	
	/**
	 * Resizes the remaining amount of work.
	 * @param size
	 * @return This instance - fluent API.
	 */
	ProgressMonitor setWorkRemaining(double size);

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
			public void worked(Status status, double work, String progressMessage, Object... data) {
				ProgressMonitor.this.worked(status, work, progressMessage, data);
				other.worked(status, work, progressMessage, data);
			}
			
			@Override
			public ProgressMonitor split(String taskName, double size, Object... data) {
				return	ProgressMonitor.this.split(taskName, size, data).compose(other.split(taskName, size, data));
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
			public ProgressMonitor setWorkRemaining(double size) {
				ProgressMonitor.this.setWorkRemaining(size);
				other.setWorkRemaining(size);
				return this;
			}
			
		};
	}
	
	/**
	 * Creates a progress monitor which multiplies work sizes passed to it by the scale. I.e. when
	 * the returned scaled monitor consumes X amount of work, this monitor consumes X / scale amount of work. 
	 * For example you want to consume 1 from this monitor and the task size is 10, then the scale is 10.
	 * @param scale
	 * @return
	 */
	default ProgressMonitor scale(double scale) {
		return new ProgressMonitor() {
			
			@Override
			public void worked(Status status, double work, String progressMessage, Object... data) {
				ProgressMonitor.this.worked(status, work / scale, progressMessage, data);
				
			}
			
			@Override
			public ProgressMonitor split(String taskName, double size, Object... data) {
				return ProgressMonitor.this.split(taskName, size / scale, data).scale(scale);
			}
			
			@Override
			public ProgressMonitor setWorkRemaining(double size) {
				ProgressMonitor.this.setWorkRemaining(size / scale);
				return this;
			}
			
			@Override
			public boolean isCancelled() {
				return ProgressMonitor.this.isCancelled();
			}
			
			@Override
			public void close() {
				ProgressMonitor.this.close();				
			}
		};
	}
		
}
