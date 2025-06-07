package org.nasdanika.graph.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Connection from an {@link EObject} instance node to its container.
 * {@link EReference} is the containment reference.
 * 
 * @author Pavel
 *
 */
public class EContainerConnection extends QualifiedConnection<EReferenceConnectionQualifier> implements Comparable<EContainerConnection> {
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EContainerConnection(
			EObjectNode source,
			EObjectNode target,
			EReference reference,
			int index) {
		
		super(source, target, reference.isContainment(), new EReferenceConnectionQualifier(reference, index), null);
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public EReference getContainmentReference() {
		return get().reference();
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public int getIndex() {
		return get().index();
	}

	@Override
	public int compareTo(EContainerConnection o) {
		return get().compareTo(o.get());
	}

}