package org.nasdanika.common;

/**
 * Information about work - name and size.
 * @author Pavel
 *
 */
public interface WorkInfo {
	
	/**
	 * @return Total number of work units. The same size is used for both ``execute()`` and ``undo()``.
	 */
	long size();
	
	/**
	 * @return Display name of the work.
	 */
	String getName();

}
