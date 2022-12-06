package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Loads EObjects Drawio diagram resources using {@link ObjectLoaderDrawioEObjectFactory}. 
 * @author Pavel
 *
 */
public abstract class ResourceSetPropertySourceEObjectFactoryResource<T extends EObject> extends PropertySourceEObjectFactoryResource<T> {

	protected ResourceSetPropertySourceEObjectFactoryResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected abstract ResourceSetPropertySourceEObjectFactory<T> getProcessorFactory();
	
	/**
	 * @return {@link ResourceSet} to load objects.
	 */
	protected abstract ResourceSet getLoadingResourceSet();	

}