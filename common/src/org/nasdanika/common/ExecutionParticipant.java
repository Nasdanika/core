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
		commit(split(progressMonitor));
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
	
	default void splitAndRollback(ProgressMonitor progressMonitor) throws Exception {
		rollback(split(progressMonitor));
	}	
	
	/**
	 * Splits the monitor for this participant.
	 * @param parent
	 * @return
	 */
	default ProgressMonitor split(ProgressMonitor parent) {
		return parent.split(name(), size(), this);
	}
		
	default Diagnostic splitAndDiagnose(ProgressMonitor progressMonitor) throws Exception {
		return diagnose(split(progressMonitor));
	}	

}
