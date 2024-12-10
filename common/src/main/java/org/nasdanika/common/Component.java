package org.nasdanika.common;

/**
 * Something that can be started, stopped, and closed
 */
public interface Component {
	
	/**
	 * Starts component activities. E.g. listening on an HTTP port, start a timer.
	 * @param progressMonitor
	 */
	void start(ProgressMonitor progressMonitor);
	
	/**
	 * Stops component activities, e.g. closes the HTTP port, stops the timer.
	 * A component may still be able to perform some functions, but it will not be initiating any new activities. 
	 * @param progressMonitor
	 */
	void stop(ProgressMonitor progressMonitor);		
	
	/**
	 * Releases resources. E.g. closes a database connection. Generally, a component is non-operational once it was closed. 
	 * @param progressMonitor
	 */
	void close(ProgressMonitor progressMonitor);		

}
