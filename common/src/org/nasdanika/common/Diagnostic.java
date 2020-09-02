package org.nasdanika.common;

import java.io.PrintStream;
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
	
	default void dump(PrintStream out, int indent) {
		for (int i=0; i < indent; ++i) {
			out.print("    ");
		}
		
		Status status = getStatus();
		if (status != null) {
			// TODO - ANSI coloring of statuses
			out.print(status.name());
			out.print(' ');
		}

		out.print(getMessage());

		List<Object> data = getData();
		if (data != null) {
			out.print(" data=");
			out.print(getData());
			for (Object de: data) {
				if (de instanceof Throwable) {
					((Throwable) de).printStackTrace(out);
				}
			}
		}		
		
		out.println();
		
	    List<Diagnostic> children = getChildren();
		if (children != null) {
	    	children.forEach(c -> c.dump(out, indent + 1));
	    }
		
	}
	
}
