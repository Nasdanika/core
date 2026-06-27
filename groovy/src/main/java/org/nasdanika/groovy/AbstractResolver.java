package org.nasdanika.groovy;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.NasdanikaResourceSet;
import org.nasdanika.groovy.dsl.DslContext.Resolver;
import org.eclipse.emf.ecore.EObject;

/**
 * Resolves against a list of {@link EPackage}s. It is used by {@link DslResourceContentsHandler} to resolve.
 */
public abstract class AbstractResolver implements Resolver {
	
	protected abstract ResourceSet getResourceSet();

	@Override
	public void global(Object id, EObject element) {
		if (getResourceSet() instanceof NasdanikaResourceSet nsdResourceSet) {
			if (id instanceof String idStr) {
				id = URI.createURI(idStr);
			} else if (!(id instanceof URI)) {
				throw new IllegalArgumentException("id must be a String or URI");
			}
			nsdResourceSet.registerGlobal((URI) id, element);
		} else {
			throw new IllegalStateException("ResourceSet is not an instance of NasdanikaResourceSet");
		}
	}
	
	@Override
	public EObject get(String id) {
		return getResourceSet().getEObject(URI.createURI(id), false);
	}
	
}