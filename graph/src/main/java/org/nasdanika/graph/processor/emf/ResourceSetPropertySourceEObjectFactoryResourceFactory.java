package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Loads {@link ResourceSetDrawioResource}'s 
 * @author Pavel
 *
 */
public abstract class ResourceSetPropertySourceEObjectFactoryResourceFactory<T extends EObject> extends PropertySourceEObjectFactoryResourceFactory<T> {
			
	protected abstract ResourceSet getResourceSet();

	@Override
	public abstract ResourceSetPropertySourceEObjectFactoryResource<T> createResource(URI uri);
	
}
