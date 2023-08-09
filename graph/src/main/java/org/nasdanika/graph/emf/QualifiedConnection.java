package org.nasdanika.graph.emf;

import org.nasdanika.graph.ObjectConnection;

/**
 * A binding of {@link ObjectConnection} source and target to {@link EObjectNode}. Path for computing URI's.
 * @param <T>
 */
public class QualifiedConnection<T> extends ObjectConnection<T> implements EObjectConnection {
	
	private String path;

	/**
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	public QualifiedConnection(EObjectNode source, EObjectNode target, boolean visitTargetNode, T qualifier, String path) {
		super(source, target, visitTargetNode, qualifier);
		this.path = path;
	}
	
	/**
	 * String value or index or a path of eKeys for many references. Null for single references
	 * @return
	 */
	public String getPath() {
		return path;
	}
	
	@Override
	public EObjectNode getTarget() {
		return (EObjectNode) super.getTarget();
	}
	
	@Override
	public EObjectNode getSource() {
		return (EObjectNode) super.getSource();
	}
	
}
