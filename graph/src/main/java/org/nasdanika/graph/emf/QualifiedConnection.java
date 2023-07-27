package org.nasdanika.graph.emf;

import java.util.Objects;

public class QualifiedConnection extends Connection {
	
	private String path;
	private int index;

	/**
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	public QualifiedConnection(EObjectNode source, EObjectNode target, int index, String path) {
		super(source, target);
		this.index = index;
		this.path = path;
	}
	
	/**
	 * String value or index or a path of eKeys for many references. Null for single references
	 * @return
	 */
	public String getPath() {
		return path;
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return super.toString() + " " + getIndex();
	}
	
}
