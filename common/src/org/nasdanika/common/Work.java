package org.nasdanika.common;

import java.util.function.Function;

/**
 * By convention Work does not split the monitor for itself - this is the responsibility of the caller.
 * 
 * {@link ProgressMonitor} allocation pattern - the caller creates a monitor with work's size and name and passes it to work's execute:
 * 
 * ```
 * try (Work work = ...; ProgressMonitor workMonitor = monitor.split(work.getName(), work.getSize(), work)) {
 *     work.execute(workMonitor);
 *     
 *     // If success
 *     work.commit();
 *     
 *     // If something went wrong
 *     work.rollback();
 *     
 * }
 * ```
 * 
 * @author Pavel Vlasov
 * @param C context type.
 * @param T result type.
 */
public interface Work<T> extends Command<T>, WorkInfo {
	
	Work<Object> NO_WORK = new Work<Object>() {

		@Override
		public double size() {
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
		
	};
	
	@SuppressWarnings("unchecked")
	static <T> Work<T> noWork() {
		return (Work<T>) NO_WORK;
	}

	/**
	 * Commits work results. The default implementation does nothing.
	 * @param progressMonitor
	 * @throws Exception
	 */
	default void commit(ProgressMonitor progressMonitor) throws Exception {
		// NOP
	}
			
	/**
	 * Rolls back all the changes done by this instance of Work. The method can be called when a composite work was
	 * cancelled or one of participants failed and the result of the entire work shall be rolled back. 
	 *  
	 * @param progressMonitor Progress monitor to report the undo progress and possibly cancel the undo operation. 
	 * @return ``true`` if the work was successfully rolled back or there was no work to roll back, ``false`` otherwise. 
	 * @throws Exception
	 */
	default boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		return true;
	};
	
	default <R> Work<R> adapt(Function<T,R> adapter) {
		return new Work<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) throws Exception {
				return adapter.apply(Work.this.execute(progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Work.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Work.this.close();
			}
			
			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				Work.this.commit(progressMonitor);
			}
			
			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return Work.this.rollback(progressMonitor);
			}
			
			@Override
			public double size() {
				return Work.this.size();
			}
			
			@Override
			public String getName() {
				return Work.this.getName();
			}
			
		};
	}
	
}
