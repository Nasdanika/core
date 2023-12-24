package org.nasdanika.graph.emf;

import org.eclipse.emf.ecore.EReference;

public record EReferenceConnectionQualifier(EReference reference, int index) implements Comparable<EReferenceConnectionQualifier> {

	@Override
	public int compareTo(EReferenceConnectionQualifier o) {
		if (equals(o)) {
			return 0;
		}
		if (reference().equals(o.reference())) {
			return index() - o.index();
		}
		
		return reference().getFeatureID() - o.reference().getFeatureID();
	}

}
