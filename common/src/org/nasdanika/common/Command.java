package org.nasdanika.common;

/**
 * Something which can be executed with reporting progress to a {@link ProgressMonitor}.
 * The command doesn't split the monitor for itself, i.e. it expects the calling code to split the monitor.
 * However, it may split it further for sub-tasks.
 * 
 * @author Pavel Vlasov
 * @param T result type.
 */
public interface Command<T> {
	
	/**
	 * This method can be invoked before the actual command execution.
	 * It may perform validations and initial setup to improve the chances of success of the execute(). 
	 * @param progressMonitor Progress monitor to report validation/setup progress and any encountered problems.
	 * @return true if all validations pass, false otherwise
	 */
	default boolean canExecute(ProgressMonitor progressMonitor) {
		return true;
	}
		
	/**
	 * Executes the command.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;	
		
}
