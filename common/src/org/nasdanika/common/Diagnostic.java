package org.nasdanika.common;

import java.util.List;

public interface Diagnostic {
	
	Status getStatus();
	
	String getMessage();
	
	List<Object> getData();
	
	List<Diagnostic> getChildren();
	
	/**
	 * Throws {@link DiagnosticException} if status is {@link Status}.ERROR
	 * @param message
	 */
	default void checkError(String message) {
		if (getStatus() == Status.ERROR) {
			throw new DiagnosticException(message, this);
		}
	}
	
	static Diagnostic compose(String message, Diagnostic... diagnostics) {
		if (diagnostics.length == 1 && diagnostics[0] != null) {
			return diagnostics[0];
		}
		
		BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, message);
		for (Diagnostic diagnostic: diagnostics) {
			ret.add(diagnostic);
		}
		return ret;
	}

}
