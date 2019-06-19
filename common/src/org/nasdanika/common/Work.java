package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 */
public interface Work<T> {
	
	Work<Object> NO_WORK = new Work<Object>() {

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Object execute(Context context, ProgressMonitor monitor) throws Exception {
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
	 * @return Total number of work units in this item.
	 */
	int size();
	
	/**
	 * 
	 * @param context
	 * @param monitor Parent monitor to use. The work is responsible for invoking monitor.split(size()).
	 * @return
	 * @throws Exception
	 */
	T execute(Context context, ProgressMonitor progressMonitor) throws Exception;
	
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
