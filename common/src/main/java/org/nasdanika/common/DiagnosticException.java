package org.nasdanika.common;

@SuppressWarnings("serial")
public class DiagnosticException extends RuntimeException {

	private Diagnostic diagnostic;

	public DiagnosticException(Diagnostic diagnostic) {
		this(diagnostic.getMessage(), diagnostic);
	}

	public DiagnosticException(String message, Diagnostic diagnostic) {
		super(message);
		this.diagnostic = diagnostic;
	}
	
	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

}
