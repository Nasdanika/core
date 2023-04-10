package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

public class EReferenceConnection implements Connection {
	
	interface Factory {
		
		EReferenceConnection create(EObjectNode source, EObjectNode taret, EReference reference, int index);
		
	}
	
	private EObjectNode source;
	private EObjectNode target;
	private EReference reference;
	private int index;

	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EReferenceConnection(EObjectNode source, EObjectNode target, EReference reference, int index) {
		this.source = source;
		this.target = target;
		this.reference = reference;
		this.index = index;
		source.getOutgoingConnections().add(this);
		target.getIncomingConnections().add(this);
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, reference.isContainment() ? Collections.singletonMap(getTarget(), getTarget().accept(visitor)) : Collections.emptyMap());
	}

	@Override
	public EObjectNode getSource() {
		return source;
	}

	@Override
	public EObjectNode getTarget() {
		return target;
	}
	
	public EReference getReference() {
		return reference;
	}
	
	public int getIndex() {
		return index;
	}

}
