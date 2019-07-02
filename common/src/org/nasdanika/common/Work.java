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
public interface Work<C,T> {
	
	Work<Object,Object> NO_WORK = new Work<Object,Object>() {

		@Override
		public long size() {
			return 0;
		}
		
		@Override
		public String getName() {
			return "No work";
		}

		@Override
		public Object execute(Object context, ProgressMonitor monitor) throws Exception {
			return null;
		}
		
		@Override
		public boolean undo(ProgressMonitor progressMonitor) throws Exception {
			return true;
		}
		
	};
	
	@SuppressWarnings("unchecked")
	static <C,T> Work<C,T> noWork() {
		return (Work<C,T>) NO_WORK;
	}
	
	/**
	 * @return Total number of work units in this item.
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
	T execute(C context, ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Splits the parent monitor using ``split()`` method and executes work using the sub-monitor. 
	 * @param context
	 * @param monitor Parent monitor to use. The work is responsible for invoking monitor.split(size()).
	 * @return
	 * @throws Exception
	 */
	default T splitAndExecute(C context, ProgressMonitor parentMonitor) throws Exception {
		try (ProgressMonitor workMonitor = split(parentMonitor)) {
			return execute(context, workMonitor);
		}
	};
	
	/**
	 * Rolls back all the changes done by this instance of Work. The method can be called when a composite work was
	 * cancelled and result of the entire work shall be rolled back. 
	 *  
	 * @param progressMonitor Progress monitor to report the undo progress and possibly cancel the undo operation. 
	 * @return ``true`` if the work was successfully rolled back or there was no work to roll back, ``false`` otherwise. 
	 * @throws Exception
	 */
	boolean undo(ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Creates a sub-monitor for this work.
	 * @param parent
	 * @return
	 */
	default ProgressMonitor split(ProgressMonitor parent) {
		return parent.split(getName(), size(), this);
	}
	
	/**
	 * Binds context to {@link Work}Work making it a {@link Command}. 
	 * @param context
	 * @return
	 */
	default Command<T> bindContext(C context) {
		return new Command<T>() {

			@Override
			public long size() {
				return Work.this.size();
			}

			@Override
			public String getName() {
				return Work.this.getName();
			}

			@Override
			public T execute(ProgressMonitor progressMonitor) throws Exception {
				return Work.this.execute(context, progressMonitor);
			}

			@Override
			public boolean undo(ProgressMonitor progressMonitor) throws Exception {
				return Work.this.undo(progressMonitor);
			}
			
		};
	}
	
}
