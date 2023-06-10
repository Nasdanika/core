package org.nasdanika.graph.emf;

import java.util.Objects;

public abstract class Connection implements org.nasdanika.graph.Connection {
	
	private EObjectNode source;
	private EObjectNode target;
	private String path;
	private int index;

	/**
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	protected Connection(EObjectNode source, EObjectNode target, int index, String path) {
		this.source = source;
		this.target = target;
		this.index = index;
		this.path = path;
		source.addOutgoingConnection(this);
		target.addIncomingConnection(this);
	}

	@Override
	public EObjectNode getSource() {
		return source;
	}

	@Override
	public EObjectNode getTarget() {
		return target;
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

	@Override
	public int hashCode() {
		return Objects.hash(getIndex(), getSource(), getTarget());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		return getIndex() == other.getIndex() && Objects.equals(getSource(), other.getSource()) && Objects.equals(getTarget(), other.getTarget());
	}	
	
}
