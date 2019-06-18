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
public interface ProgressMonitor extends AutoCloseable {
	
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
	 * @param ticks
	 * @return
	 */
	ProgressMonitor split(String taskName, int ticks);
	
	/**
	 * Reports progress.
	 * @param work Work installment - how much work was done since the last report, not the total work.
	 * @param progressMessage Progress message. 
	 */
	void worked(int work, String progressMessage);
		
}
