package org.nasdanika.graph.emf;

import org.nasdanika.graph.ObjectConnection;

/**
 * A binding of {@link ObjectConnection} source and target to {@link EObjectNode}. Path for computing URI's.
 * @param <T>
 */
public class QualifiedConnection<T> extends EObjectValueConnection<T> {
	
	private String path;

	public QualifiedConnection(
			EObjectNode source, 
			EObjectNode target, 
			boolean visitTargetNode, 
			T qualifier, 
			String path) {
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
	
}
