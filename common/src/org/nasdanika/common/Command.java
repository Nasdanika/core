package org.nasdanika.common;

/**
 * {@link ProgressMonitor} allocation pattern - the caller creates a monitor with work's size and name and passes it to work's execute:
 * 
 * ```
 * try (ProgressMonitor workMonitor = monitor.split(work.getName(), work.getSize(), work)) {
 *     work.execute(context, workMonitor);
 * }
 * ```
 * 
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Command<T> {
	
	Command<Object> NOP = new Command<Object>() {

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
	static <T> Command<T> nop() {
		return (Command<T>) NOP;
	}
	
	/**
	 * @return Total number of work units in this item.
	 */
	long size();
	
	/**
	 * @return Display name of the command.
	 */
	String getName();
	
	/**
	 * Executes command.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Splits the parent monitor using ``split()`` method and executes work using the sub-monitor. 
	 * @param context
	 * @param monitor Parent monitor to use. The work is responsible for invoking monitor.split(size()).
	 * @return
	 * @throws Exception
	 */
	default T splitAndExecute(ProgressMonitor parentMonitor) throws Exception {
		try (ProgressMonitor commandMonitor = split(parentMonitor)) {
			return execute(commandMonitor);
		}
	};
	
	/**
	 * Rolls back all the changes done by this instance of command. The method can be called when a composite command was
	 * cancelled and result all the changes shall be rolled back. 
	 *  
	 * @param progressMonitor Progress monitor to report the undo progress and possibly cancel the undo operation. 
	 * @return ``true`` if the changes were successfully rolled back or there were no changes to roll back, ``false`` otherwise. 
	 * @throws Exception
	 */
	boolean undo(ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Creates a sub-monitor for this command.
	 * @param parent
	 * @return
	 */
	default ProgressMonitor split(ProgressMonitor parent) {
		return parent.split(getName(), size(), this);
	}
	
}
