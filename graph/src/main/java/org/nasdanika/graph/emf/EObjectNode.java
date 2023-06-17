package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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

	private List<EObject> targets = Collections.synchronizedList(new ArrayList<>());
	private Collection<org.nasdanika.graph.Connection> incomingConnections = Collections.synchronizedCollection(new HashSet<>());
	private Collection<org.nasdanika.graph.Connection> outgoingConnections = Collections.synchronizedCollection(new HashSet<>());

	@SuppressWarnings("unchecked")
	public EObjectNode(
			EObject target, 
			BiFunction<EObject, ProgressMonitor,ResultRecord> nodeFactory, 
			EReferenceConnection.Factory referenceConnectionFactory, 
			EOperationConnection.Factory operationConnectionFactory, 
			ProgressMonitor progressMonitor) {
		this.targets.add(target);
		
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
	protected void resolve(Function<EObject,EObjectNode> registry, EReferenceConnection.Factory referenceConnectionFactory, ProgressMonitor progressMonitor) {		
		synchronized (targets) {
			for (EObject target: targets) {
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
				
				EClass eClass = target.eClass();
				if (eClass != target) {
					EObjectNode eClassNode = registry.apply(eClass);
					if (eClassNode != null) {
						new EClassConnection(this, eClassNode);
					}			
				}
			}
		}
	}
	
	public EObject getTarget() {
		return targets.get(0);
	}
	
	public List<EObject> getTargets() {
		return targets;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<org.nasdanika.graph.Connection, T> results = new LinkedHashMap<>();
		synchronized (outgoingConnections) {
			for (org.nasdanika.graph.Connection connection: outgoingConnections) {
				results.put(connection, connection.accept(visitor));
			}
		}
		return visitor.apply(this, results);
	}

	@Override
	public Collection<org.nasdanika.graph.Connection> getIncomingConnections() {
		return incomingConnections;
	}

	@Override
	public Collection<org.nasdanika.graph.Connection> getOutgoingConnections() {
		return outgoingConnections;
	}
		
	public void addIncomingConnection(org.nasdanika.graph.Connection connection) {
		synchronized (incomingConnections) {
			incomingConnections.add(connection);
		}
	}

	public void addOutgoingConnection(org.nasdanika.graph.Connection connection) {
		synchronized (outgoingConnections) {
			outgoingConnections.add(connection);
		}
	}	

	@Override
	public Object getProperty(String name) {
		synchronized (targets) {
			for (EObject target: targets) {
				EStructuralFeature sf = target.eClass().getEStructuralFeature(name);
				if (sf instanceof EAttribute) {
					return getTarget().eGet(sf);
				}				
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getTargets() + " incomingConnections: " + getIncomingConnections().size() + ", outgoingConnections: " + getOutgoingConnections().size();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(targets);
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
		return Objects.equals(targets, other.targets);
	}

}
