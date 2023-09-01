package org.nasdanika.graph.emf;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ObjectNode;

public class EObjectNode extends ObjectNode<EObject> implements PropertySource<String, Object> {
	
	/**
	 * 
	 * @param element
	 * @param parallel
	 * @param elementProvider
	 * @param factory To delegate creation of connections
	 * @param parallelAccept If true, created node uses parallel stream in accept()
	 * @param progressMonitor
	 */
	@SuppressWarnings("unchecked")
	public EObjectNode(
			EObject value,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Element>,ProgressMonitor>> registry,
			EObjectGraphFactory factory,
			ProgressMonitor progressMonitor) {
		
		super(value);
		
		for (EReference eReference: value.eClass().getEAllReferences()) {
			Object val = value.eGet(eReference);
			if (val != null) {
				if (eReference.isMany()) {
					int counter = 0;
					for (EObject element: (Collection<EObject>) val) {
						int idx = counter++;
						if (element != null) {
							elementProvider.accept(element, (targetNode, pm) -> factory.createEReferenceConnection(this, (EObjectNode) targetNode, idx, eReference, pm));
						}
					}
				} else {
					elementProvider.accept((EObject) val, (targetNode, pm) -> factory.createEReferenceConnection(this, (EObjectNode) targetNode, -1, eReference, pm));
				}
			}
		}
	
		for (EOperation eOperation: get().eClass().getEAllOperations()) {
			factory.createEOperationConnections(this, eOperation, parallel, elementProvider, progressMonitor);
		}		
		
		EClass eClass = value.eClass();
		if (eClass != value) {
			elementProvider.accept(eClass, (targetNode, pm) -> factory.createEClassConnection(this, (EObjectNode) targetNode, pm));
		}
		
		EObject eContainer = value.eContainer();
		if (eContainer != null) {
			elementProvider.accept(eContainer, (targetNode, pm) -> factory.createEContainerConnection(this, (EObjectNode) targetNode, pm));
		}

	}

	@Override
	public Object getProperty(String name) {
		EStructuralFeature sf = get().eClass().getEStructuralFeature(name);
		if (sf instanceof EAttribute) {
			return get().eGet(sf);
		}				
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + get() + " incomingConnections: " + getIncomingConnections().size() + ", outgoingConnections: " + getOutgoingConnections().size();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<EObjectConnection> getIncomingConnections() {
		return (Collection) super.getIncomingConnections();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<EObjectConnection> getOutgoingConnections() {
		return (Collection) super.getOutgoingConnections();
	}

}
