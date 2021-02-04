package org.nasdanika.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Creates {@link YamlResourceImpl}
 * @author Pavel
 *
 */
public class YamlResourceFactoryImpl extends ResourceFactoryImpl {
	
	private ProgressMonitor progressMonitor;
	private ObjectLoader loader;

	public YamlResourceFactoryImpl(ObjectLoader loader, ProgressMonitor progressMonitor) {
		this.loader = loader;
		this.progressMonitor = progressMonitor;
	}

	@Override
	public Resource createResource(URI uri) {
		return new YamlResourceImpl(uri, loader, progressMonitor);
	}
	
}
