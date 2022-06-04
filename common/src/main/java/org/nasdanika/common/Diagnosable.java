package org.nasdanika.common;

import java.util.Collections;
import java.util.List;

/**
 * Something which can diagnose/validate itself.
 * @author Pavel
 *
 */
public interface Diagnosable {
	
	Diagnostic SUCCESS = new Diagnostic() {
		
		@Override
		public Status getStatus() {
			return Status.SUCCESS;
		}
		
		@Override
		public String getMessage() {
			return null;
		}
		
		@Override
		public List<Object> getData() {
			return null;
		}
		
		@Override
		public List<Diagnostic> getChildren() {
			return Collections.emptyList();
		}
	}; 
	
	/**
	 * Performs self-diagnostic.
	 * @return Diagnostic result.
	 */
	default Diagnostic diagnose(ProgressMonitor progressMonitor) {
		return SUCCESS;
	};

}
