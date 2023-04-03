package org.nasdanika.common;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public interface Diagnostic extends Composeable<Diagnostic> {
	
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
	
	default void dump(PrintStream out, int indent, Status... statuses) {
		for (int i=0; i < indent; ++i) {
			out.print("    ");
		}
				
		Status status = getStatus();
		boolean match = statuses.length == 0;
		for (Status s: statuses) {
			if (s == status) {
				match = true;
				break;
			}
		}
		if (match) {
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
		    	children.forEach(c -> c.dump(out, indent + 1, statuses));
		    }
		}		
	}
	
	@Override
	default Diagnostic compose(Diagnostic other) {
		if (other == null || other == this || other == SUCCESS) {
			return this;
		}
		
		if (this == SUCCESS) {
			return other;
		}
		
		BasicDiagnostic composed = new BasicDiagnostic(Status.SUCCESS, null);
		composed.add(this);
		composed.add(other);
		return composed;
	}
	
}
