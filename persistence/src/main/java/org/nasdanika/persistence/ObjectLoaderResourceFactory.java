package org.nasdanika.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

/**
 * Creates {@link YamlResource}
 * @author Pavel
 *
 */
public abstract class ObjectLoaderResourceFactory extends ResourceFactoryImpl {
	
	@Override
	public Resource createResource(URI uri) {
		return new ObjectLoaderResource(uri) {

			@Override
			protected ObjectLoader getObjectLoader() {
				return ObjectLoaderResourceFactory.this.getObjectLoader(getURI());
			}

			@Override
			protected ProgressMonitor getProgressMonitor() {
				return ObjectLoaderResourceFactory.this.getProgressMonitor(getURI());
			}
			
		};
	}
	
	protected abstract ObjectLoader getObjectLoader(URI uri);
	
	protected ProgressMonitor getProgressMonitor(URI uri) {
		return new NullProgressMonitor();
	}
	
}
