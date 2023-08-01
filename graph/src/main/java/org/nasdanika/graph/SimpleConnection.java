package org.nasdanika.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Adds itself to source and target in constructor
 */
public class SimpleConnection implements Connection {
	
	private Node source;
	private Node target;
	private boolean visitTarget;

	/**
	 * @param source
	 * @param target
	 * @param visitTarget If true, accept() passes visitor to the target.
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SimpleConnection(Node source, Node target, boolean visitTarget) {
		this.source = source;
		this.target = target;
		if (source != null) {
			((Collection) source.getOutgoingConnections()).add(this);
		}
		if (target != null) {
			((Collection) target.getIncomingConnections()).add(this);
		}
		this.visitTarget = visitTarget;
	}
	
	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {		
		return visitor.apply(this, visitTarget ? Collections.singletonMap(getTarget(), getTarget().accept(visitor)) : Collections.emptyMap());
	}

	@Override
	public Node getSource() {
		return source;
	}

	@Override
	public Node getTarget() {
		return target;
	}

}
