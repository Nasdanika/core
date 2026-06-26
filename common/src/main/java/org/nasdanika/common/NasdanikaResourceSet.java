package org.nasdanika.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Supports registration of global objects.
 */
public class NasdanikaResourceSet extends ResourceSetImpl {
	
	private Map<URI, EObject> globals = new ConcurrentHashMap<>();
	
	public void registerGlobal(URI uri, EObject global) {
		globals.put(uri, global);
	}

	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		return globals.getOrDefault(uri, super.getEObject(uri, loadOnDemand));
	}
	
}
