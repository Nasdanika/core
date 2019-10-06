package org.nasdanika.common;

/**
 * 
 * @author Pavel
 *
 */
public interface DiagnosticChain {

	/**
	 * Adds the diagnostic to the chain.
	 */
	void add(Diagnostic diagnostic);

	/**
	 * Adds the {@link Diagnostic#getChildren children} of the diagnostic to the
	 * chain.
	 */
	void addAll(Diagnostic diagnostic);

}
