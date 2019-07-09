package org.nasdanika.common;

/**
 * Something which can be executed with reporting progress to a {@link ProgressMonitor}.
 * The command doesn't split the monitor for itself, i.e. it expects the calling code splitting the monitor.
 * However, it may split it further for sub-tasks.
 * 
 * @author Pavel Vlasov
 * @param T result type.
 */
public interface Command<T> {
		
	/**
	 * Executes the command.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;	
		
}
