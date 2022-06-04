package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicDiagnostic implements Diagnostic, DiagnosticChain, Composeable<Diagnostic> {
	
	private List<Object> data = new ArrayList<>();
	private Status status;
	private String message;
	private List<Diagnostic> children = new ArrayList<>();
	
	public BasicDiagnostic(Status status, String message, Object... data) {
		this.status = status;
		this.message = message;
		for (Object d: data) {
			this.data.add(d);
		}
	}

	@Override
	public void add(Diagnostic diagnostic) {
		if (diagnostic != null) {
			children.add(diagnostic);
		}		
	}

	@Override
	public void addAll(Diagnostic diagnostic) {
		if (diagnostic != null) {
			children.addAll(diagnostic.getChildren());
		}		
	}

	@Override
	public Status getStatus() {
		return children.stream().map(Diagnostic::getStatus).reduce(status, (s1, s2) -> {
			if (s1 == null) {
				return s2;
			}
			if (s2 == null) {
				return s1;
			}
			return s1.ordinal() > s2.ordinal() ? s1 : s2;
		});
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public List<Object> getData() {
		return data;
	}

	@Override
	public List<Diagnostic> getChildren() {
		return Collections.unmodifiableList(children);
	}
		
	/**
	 * If the diagnostic has {@link Diagnostic#getChildren children}, {@link #addAll add}s those children, otherwise, {@link #add add}s the diagnostic.
	 */
	@Override
	public Diagnostic compose(Diagnostic other) {
		if (other != null) {
			if (other.getChildren().isEmpty()) {
				add(other);
			} else {
				addAll(other);
			}
		}
		return this;
	}
		
}
