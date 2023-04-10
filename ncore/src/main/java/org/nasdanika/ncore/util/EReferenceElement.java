package org.nasdanika.ncore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public record EReferenceElement(EObject source, EObject target, EReference eReference, int position) {
	
}
