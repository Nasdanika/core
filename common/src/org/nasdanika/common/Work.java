package org.nasdanika.common;

/**
 * By convention Work does not split the monitor for itself - this is the responsibility of the caller.
 * 
 * {@link ProgressMonitor} allocation pattern - the caller creates a monitor with work's size and name and passes it to work's execute:
 * 
 * ```
 * try (ProgressMonitor workMonitor = monitor.split(work.getName(), work.getSize(), work)) {
 *     work.execute(workMonitor);
 * }
 * ```
 * 
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Work<T> {
	
	Work<Object> NO_WORK = new Work<Object>() {

		@Override
		public long size() {
			return 0;
		}
		
		@Override
		public String getName() {
			return "No work";
		}

		@Override
		public Object execute(ProgressMonitor monitor) throws Exception {
			return null;
		}
		
		@Override
		public boolean undo(ProgressMonitor progressMonitor) throws Exception {
			return true;
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <T> Work<T> noWork() {
		return (Work<T>) NO_WORK;
	}
	
	/**
	 * @return Total number of work units. The same size is used for both ``execute()`` and ``undo()``.
	 */
	long size();
	
	/**
	 * @return Display name of the work.
	 */
	String getName();
	
	/**
	 * Executes work.
	 * @param context
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Rolls back all the changes done by this instance of Work. The method can be called when a composite work was
	 * cancelled and result of the entire work shall be rolled back. 
	 *  
	 * @param progressMonitor Progress monitor to report the undo progress and possibly cancel the undo operation. 
	 * @return ``true`` if the work was successfully rolled back or there was no work to roll back, ``false`` otherwise. 
	 * @throws Exception
	 */
	boolean undo(ProgressMonitor progressMonitor) throws Exception;
			
}
