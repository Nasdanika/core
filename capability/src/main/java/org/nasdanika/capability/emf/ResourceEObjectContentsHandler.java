package org.nasdanika.capability.emf;

import org.eclipse.emf.ecore.EObject;

public interface ResourceEObjectContentsHandler<T extends EObject> extends ResourceContentsHandler<T> {
		
	@SuppressWarnings("unchecked")
	default ResourceContentsHandler<EObject[]> adaptToEObjectArrayContentsHandler() {
		return adapt(eObj -> new EObject[] { eObj }, a -> a.length > 0 ? (T) a[0] : null);
	}	

}
