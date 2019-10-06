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
	double size();
	
	/**
	 * @return Display name of the work.
	 */
	String getName();
	
	/**
	 * @return Work {@link Descriptor}. The descriptor can be used to auto-generate work input UI.
	 */
	default Descriptor getDescriptor() {
		return null;
	}

}
