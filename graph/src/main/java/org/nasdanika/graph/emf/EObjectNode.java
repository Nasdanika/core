package org.nasdanika.graph.emf;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

public class EObjectNode implements Node, PropertySource<String, Object> {
	
	@Override
	public int hashCode() {
		return Objects.hash(target);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EObjectNode other = (EObjectNode) obj;
		return Objects.equals(target, other.target);
	}

	private EObject target;
	private Collection<EReferenceConnection> incomingConnections = new HashSet<>();
	private Collection<EReferenceConnection> outgoingConnections = new HashSet<>();

	@SuppressWarnings("unchecked")
	public EObjectNode(EObject target, Function<EObject,EObjectNode> nodeFactory, EReferenceConnection.Factory connectionFactory) {
		this.target = target;
		
		for (EReference eReference: target.eClass().getEAllReferences()) {
			if (eReference.isContainment()) {
				Object val = target.eGet(eReference);
				if (val != null) {
					if (eReference.isMany()) {
						int idx = 0;
						for (EObject element: (Collection<EObject>) val) {
							EObjectNode targetNode = nodeFactory.apply(element);;
							connectionFactory.create(this, targetNode, eReference, idx++);							
						}
					} else {
						EObjectNode targetNode = nodeFactory.apply((EObject) val);
						connectionFactory.create(this, targetNode, eReference, -1);
					}
				}
			}
		}		
	}
	
	/**
	 * Creates connections for non-containment references.
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	void resolve(Function<EObject,EObjectNode> registry, EReferenceConnection.Factory connectionFactory) {		
		for (EReference eReference: target.eClass().getEAllReferences()) {
			if (!eReference.isContainment()) {
				Object val = target.eGet(eReference);
				if (val != null) {
					if (eReference.isMany()) {
						int idx = 0;
						for (EObject element: (Collection<EObject>) val) {
							EObjectNode targetNode = registry.apply(element);
							if (targetNode != null) {
								connectionFactory.create(this, targetNode, eReference, idx++);
							}
						}
					} else {
						EObjectNode targetNode = registry.apply((EObject) val);
						if (targetNode != null) {
							connectionFactory.create(this, targetNode, eReference, -1);
						}
					}
				}
			}
		}		
	}
	
	public EObject getTarget() {
		return target;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<EReferenceConnection, T> results = new LinkedHashMap<>();
		for (EReferenceConnection connection: getOutgoingConnections()) {
			results.put(connection, connection.accept(visitor));
		}
		return visitor.apply(this, results);
	}

	@Override
	public Collection<EReferenceConnection> getIncomingConnections() {
		return incomingConnections;
	}

	@Override
	public Collection<EReferenceConnection> getOutgoingConnections() {
		return outgoingConnections;
	}

	@Override
	public Object getProperty(String name) {
		EStructuralFeature sf = getTarget().eClass().getEStructuralFeature(name);
		return sf instanceof EAttribute ? getTarget().eGet(sf) : null;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getTarget() + " incomingConnections: " + getIncomingConnections().size() + ", outgoingConnections: " + getOutgoingConnections().size();
	}

}
