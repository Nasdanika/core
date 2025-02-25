package org.nasdanika.common;

public interface Closeable {
	
	/**
	 * Releases resources. E.g. closes a database connection. Generally, a component is non-operational once it was closed. 
	 * @param progressMonitor
	 */
	void close(ProgressMonitor progressMonitor);

}
