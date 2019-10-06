package org.nasdanika.common;

/**
 * Something that can diagnose/validate T
 * @author Pavel
 *
 */
public interface Diagnostician<T> {
	
	/**
	 * Diagnoses the first argument in the context of the second.
	 * @param obj Object to be diagnosed/validated.
	 * @param context Source of contextual information.
	 * @return Diagnostic results.
	 */
	Diagnostic diagnose(T obj, Context context);

}
