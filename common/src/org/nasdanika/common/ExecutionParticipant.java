package org.nasdanika.common;

/**
 * Base interface for {@link Supplier}, {@link Function}, {@link Consumer}, and {@link _LegacyCommandToRemove}.
 * @author Pavel
 *
 */
public interface ExecutionParticipant extends Diagnosable, ExecutionParticipantInfo, AutoCloseable {
	
	
	@Override
	default void close() throws Exception {
		
	}

	/**
	 * Commits work results. The default implementation does nothing.
	 * @param progressMonitor
	 * @throws Exception
	 */
	default void commit(ProgressMonitor progressMonitor) throws Exception {
		// NOP
	}
		
	default void splitAndCommit(ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Commiting "+name())) {
			commit(subMonitor);
		}
	}	
			
	default void splitAndCommit(double size, ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Commiting "+name())) {
			commit(subMonitor);
		}
	}	
			
	/**
	 * Rolls back all the changes done by this instance of Supplier. The method can be called when a composite work was
	 * cancelled or one of participants failed and the result of the entire work shall be rolled back. 
	 *  
	 * @param progressMonitor Progress monitor to report the undo progress and possibly cancel the undo operation. 
	 * @return ``true`` if the work was successfully rolled back or there was no work to roll back, ``false`` otherwise. 
	 * @throws Exception
	 */
	default boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		return true;
	};
	
	default boolean splitAndRollback(ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Rolling back "+name())) {
			return rollback(subMonitor);
		}
	}	
	
	default boolean splitAndRollback(double size, ProgressMonitor progressMonitor) throws Exception {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Rolling back "+name())) {
			return rollback(subMonitor);
		}
	}	
	
	/**
	 * Splits the monitor for this participant's size and name.
	 * @param parent
	 * @param taskName Task name, e.g. diagnose. If null then execution participant name is used as the task name.
	 * @return
	 */
	default ProgressMonitor split(ProgressMonitor parent, String taskName) {
		return parent.split(Util.isBlank(taskName) ? name() : taskName, size(), this);
	}
		
	/**
	 * Splits the monitor for the specified size and this participant's name, then resizes the result to the participant size.
	 * @param size
	 * @param parent
	 * @return
	 */
	default ProgressMonitor split(double size, ProgressMonitor parent, String taskName) {
		return parent.split(Util.isBlank(taskName) ? name() : taskName, size, this).setWorkRemaining(size());
	}
		
	default Diagnostic splitAndDiagnose(ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Diagnosing "+name())) {
			return diagnose(subMonitor);
		}
	}	
	
	default Diagnostic splitAndDiagnose(double size, ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(size, progressMonitor, "Diagnosing "+name())) {
			return diagnose(subMonitor);
		}
	}	
	
}
