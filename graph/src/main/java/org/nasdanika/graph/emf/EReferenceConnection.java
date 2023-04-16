package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

public class EReferenceConnection implements Connection {
	
	public interface Factory {
		
		EReferenceConnection create(EObjectNode source, EObjectNode target, EReference reference, int index);
		
	}
	
	private EObjectNode source;
	private EObjectNode target;
	private EReference reference;
	private int index;
	private String path;

	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EReferenceConnection(EObjectNode source, EObjectNode target, EReference reference, int index, String path) {
		this.source = source;
		this.target = target;
		this.reference = reference;
		this.index = index;
		this.path = path;
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
	
	@Override
	public String toString() {
		return super.toString() + " " + reference.getName() + " " + index;
	}
	
	/**
	 * String value or index or a path of eKeys for many references. Null for single references
	 * @return
	 */
	public String getPath() {
		return path;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, reference, source, target);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EReferenceConnection other = (EReferenceConnection) obj;
		return index == other.index && Objects.equals(reference, other.reference) && Objects.equals(source, other.source) && Objects.equals(target, other.target);
	}

}
