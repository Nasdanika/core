package org.nasdanika.common;

/**
 * Something which can diagnose/validate itself.
 * @author Pavel
 *
 */
public interface Diagnosable {
	
	/**
	 * Performs self-diagnostic.
	 * @return Diagnostic result.
	 */
	default Diagnostic diagnose(ProgressMonitor progressMonitor) {
		return new BasicDiagnostic(Status.SUCCESS, "Diagnostic of "+this, this);
	};

}
