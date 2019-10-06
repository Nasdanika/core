package org.nasdanika.common;

/**
 * Something which can diagnose/validate itself.
 * @author Pavel
 *
 */
public interface Diagnosable {
	
	/**
	 * Performs self-diagnostic.
	 * @param context Source of contextual information.
	 * @return Diagnostic result.
	 */
	Diagnostic diagnose(Context context);

}
