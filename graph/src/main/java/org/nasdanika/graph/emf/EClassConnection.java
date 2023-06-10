package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.Element;

/**
 * Connection from an {@link EObject} instance node to its {@link EClass} node.
 * @author Pavel
 *
 */
public class EClassConnection implements org.nasdanika.graph.Connection {
	
	private EObjectNode source;
	private EObjectNode target;

	/**
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	protected EClassConnection(EObjectNode source, EObjectNode target) {
		this.source = source;
		this.target = target;
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
	
	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, Collections.emptyMap());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSource(), getTarget());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EClassConnection other = (EClassConnection) obj;
		return Objects.equals(getSource(), other.getSource()) && Objects.equals(getTarget(), other.getTarget());
	}
	
}
