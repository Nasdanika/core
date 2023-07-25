package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

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
	
	static Map<EObject, EObjectNode> instances = new ConcurrentHashMap<>(); // TODO - remove after testing
	
	private EObject target;
	private Collection<org.nasdanika.graph.Connection> incomingConnections = Collections.synchronizedCollection(new HashSet<>());
	private Collection<org.nasdanika.graph.Connection> outgoingConnections = Collections.synchronizedCollection(new HashSet<>());
	private boolean parallel;
	private int hashCode;

	/**
	 * 
	 * @param element
	 * @param parallel
	 * @param elementProvider
	 * @param stageConsumer
	 * @param factory To delegate creation of connections
	 * @param parallelAccept If true, created node uses parallel stream in accept()
	 * @param progressMonitor
	 */
	@SuppressWarnings("unchecked")
	public EObjectNode(
			EObject target,
			boolean parallel,
			Function<EObject, CompletionStage<Element>> elementProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			EObjectGraphFactory factory,
			ProgressMonitor progressMonitor) {
		
		EObjectNode prev = instances.put(target, this);
		if (prev != null) {
			throw new IllegalStateException("Already there: " + prev);
		}
		
		this.target = target;
		hashCode = Objects.hash(target);
		
		this.parallel = parallel;
		
		for (EReference eReference: target.eClass().getEAllReferences()) {
			Object val = target.eGet(eReference);
			if (val != null) {
				if (eReference.isMany()) {
					int counter = 0;
					for (EObject element: (Collection<EObject>) val) {
						int idx = counter++;
						stageConsumer.accept(elementProvider.apply(element).thenAccept(targetNode -> factory.createEReferenceConnection(this, (EObjectNode) targetNode, idx, eReference, progressMonitor)));
					}
				} else {
					stageConsumer.accept(elementProvider.apply((EObject) val).thenAccept(targetNode -> factory.createEReferenceConnection(this, (EObjectNode) targetNode, -1, eReference, progressMonitor)));
				}
			}
		}
	
		for (EOperation eOperation: target.eClass().getEAllOperations()) {
			factory.createEOperationConnections(this, eOperation, parallel, elementProvider, stageConsumer, progressMonitor);
		}		
		
		EClass eClass = target.eClass();
		if (eClass != target) {
			stageConsumer.accept(elementProvider.apply(eClass).thenAccept(targetNode -> factory.createEClassConnection(this, (EObjectNode) targetNode, progressMonitor)));
		}

	}
	
	public EObject getTarget() {
		return target;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<org.nasdanika.graph.Connection, T> results = new LinkedHashMap<>();
		
		List<org.nasdanika.graph.Connection> occ; // snapshot
		synchronized (outgoingConnections) {
			occ = new ArrayList<>(outgoingConnections);
		}
		Stream<org.nasdanika.graph.Connection> ocStream = parallel ? occ.parallelStream() : occ.stream();
		record Result<R>(org.nasdanika.graph.Connection connection, R result) {}
		ocStream
			.map(connection -> new Result<T>(connection, connection.accept(visitor)))
			.toList()			
			.forEach(result -> results.put(result.connection(), result.result()));
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
		EStructuralFeature sf = target.eClass().getEStructuralFeature(name);
		if (sf instanceof EAttribute) {
			return getTarget().eGet(sf);
		}				
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getTarget() + " incomingConnections: " + getIncomingConnections().size() + ", outgoingConnections: " + getOutgoingConnections().size();
	}
	
	@Override
	public int hashCode() {
		return hashCode;
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
		return Objects.equals(target, other.getTarget());
	}

}
