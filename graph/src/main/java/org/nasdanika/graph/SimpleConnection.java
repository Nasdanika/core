package org.nasdanika.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Adds itself to source and target in constructor
 */
public class SimpleConnection implements Connection {
	
	private Node source;
	private Node target;
	private boolean visitTarget;
	
	private Supplier<Collection<? extends Element>> childrenSupplier;

	public SimpleConnection(
			Node source, 
			Node target, 
			boolean visitTarget) {
		this(source, target, visitTarget, Collections.emptyList());
	}

	public SimpleConnection(
			Node source, 
			Node target, 
			boolean visitTarget, 
			Collection<? extends Element> children) {
		
		this(
				source,
				target,
				visitTarget,
				() -> children == null ? Collections.emptyList() : children);
	}
	
	@Override
	public Collection<? extends Element> getChildren() {
		Collection<? extends Element> children = childrenSupplier.get();
		return children;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public SimpleConnection(
			Node source, 
			Node target, 
			boolean visitTarget, 
			Supplier<Collection<? extends Element>> childrenSupplier) {
		
		this.source = source;
		this.target = target;
		if (source != null) {
			((Collection) source.getOutgoingConnections()).add(this);
		}
		if (target != null) {
			((Collection) target.getIncomingConnections()).add(this);
		}
		this.visitTarget = visitTarget;
		this.childrenSupplier = childrenSupplier == null ? () -> Collections.emptyList() : childrenSupplier;
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
