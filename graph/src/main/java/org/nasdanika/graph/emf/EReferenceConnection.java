package org.nasdanika.graph.emf;

import org.eclipse.emf.ecore.EReference;

public class EReferenceConnection extends QualifiedConnection<EReferenceConnectionQualifier> {
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EReferenceConnection(
			EObjectNode source,
			EObjectNode target,
			EReference reference,
			int index,
			String path) {
		
		super(source, target, reference.isContainment(), new EReferenceConnectionQualifier(reference, index), path);
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public EReference getReference() {
		return get().reference();
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public int getIndex() {
		return get().index();
	}

}
