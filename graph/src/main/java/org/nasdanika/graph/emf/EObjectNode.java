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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

public class EObjectNode implements Node, PropertySource<String, Object> {
	
	/**
	 * isNew is true if a new node was crated, false if it was retrieved from a registry of existing nodes
	 * @author Pavel
	 *
	 */
	public static record ResultRecord(EObjectNode node, boolean isNew) {}	
	
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
	private Collection<Connection> incomingConnections = new HashSet<>();
	private Collection<Connection> outgoingConnections = new HashSet<>();

	@SuppressWarnings("unchecked")
	public EObjectNode(
			EObject target, 
			BiFunction<EObject, ProgressMonitor,ResultRecord> nodeFactory, 
			EReferenceConnection.Factory referenceConnectionFactory, 
			EOperationConnection.Factory operationConnectionFactory, 
			ProgressMonitor progressMonitor) {
		this.target = target;
		
		for (EReference eReference: target.eClass().getEAllReferences()) {
			if (eReference.isContainment()) {
				Object val = target.eGet(eReference);
				if (val != null) {
					if (eReference.isMany()) {
						int idx = 0;
						for (EObject element: (Collection<EObject>) val) {
							EObjectNode targetNode = nodeFactory.apply(element, progressMonitor).node();
							referenceConnectionFactory.create(this, targetNode, idx++, eReference, progressMonitor);							
						}
					} else {
						EObjectNode targetNode = nodeFactory.apply((EObject) val, progressMonitor).node();
						referenceConnectionFactory.create(this, targetNode, -1, eReference, progressMonitor);
					}
				}
			}
		}
	
		if (operationConnectionFactory != null) {
			for (EOperation eOperation: target.eClass().getEAllOperations()) {
				operationConnectionFactory.create(this, eOperation, nodeFactory, progressMonitor);
			}		
		}		
	}
	
	/**
	 * Creates connections for non-containment references.
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	void resolve(Function<EObject,EObjectNode> registry, EReferenceConnection.Factory referenceConnectionFactory, ProgressMonitor progressMonitor) {		
		for (EReference eReference: target.eClass().getEAllReferences()) {
			if (!eReference.isContainment()) {
				Object val = target.eGet(eReference);
				if (val != null) {
					if (eReference.isMany()) {
						int idx = 0;
						for (EObject element: (Collection<EObject>) val) {
							EObjectNode targetNode = registry.apply(element);
							if (targetNode != null) {
								referenceConnectionFactory.create(this, targetNode, idx++, eReference, progressMonitor);
							}
						}
					} else {
						EObjectNode targetNode = registry.apply((EObject) val);
						if (targetNode != null) {
							referenceConnectionFactory.create(this, targetNode, -1, eReference, progressMonitor);
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
		Map<Connection, T> results = new LinkedHashMap<>();
		for (Connection connection: getOutgoingConnections()) {
			results.put(connection, connection.accept(visitor));
		}
		return visitor.apply(this, results);
	}

	@Override
	public Collection<Connection> getIncomingConnections() {
		return incomingConnections;
	}

	@Override
	public Collection<Connection> getOutgoingConnections() {
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
