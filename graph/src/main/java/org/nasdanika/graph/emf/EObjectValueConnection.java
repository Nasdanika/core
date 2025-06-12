package org.nasdanika.graph.emf;

import org.nasdanika.graph.ObjectConnection;

public class EObjectValueConnection<T> extends ObjectConnection<T> implements EObjectConnection {

	public EObjectValueConnection(
			EObjectNode source, 
			EObjectNode target, 
			boolean visitTargetNode, 
			T value) {
		super(source, target, visitTargetNode, value);
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
