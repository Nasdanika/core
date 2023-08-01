package org.nasdanika.graph.emf;

import org.nasdanika.graph.SimpleConnection;

/**
 * A binding of {@link SimpleConnection} to {@link EObjectNode} source and target.
 */
public class Connection extends SimpleConnection implements EObjectConnection {
	
	/**
	 * @param source
	 * @param target
	 */
	public Connection(EObjectNode source, EObjectNode target, boolean visitTarget) {
		super (source, target, visitTarget);
	}

	@Override
	public EObjectNode getSource() {
		return (EObjectNode) super.getSource();
	}

	@Override
	public EObjectNode getTarget() {
		return (EObjectNode) super.getTarget();
	}
	
}
