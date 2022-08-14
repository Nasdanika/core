package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Creates {@link YamlResource}
 * @author Pavel
 *
 */
public class JsonResourceFactory extends ResourceFactoryImpl {
	
	private ProgressMonitor progressMonitor;
	private ObjectLoader loader;
	private Context context;

	public JsonResourceFactory(ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		this.loader = loader;
		this.context = context;
		this.progressMonitor = progressMonitor;
	}

	@Override
	public Resource createResource(URI uri) {
		return new JsonResource(uri, loader, context, progressMonitor);
	}
	
}
