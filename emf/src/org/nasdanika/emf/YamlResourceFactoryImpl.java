package org.nasdanika.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.Context;
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
	private Context context;

	public YamlResourceFactoryImpl(ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		this.loader = loader;
		this.context = context;
		this.progressMonitor = progressMonitor;
	}

	@Override
	public Resource createResource(URI uri) {
		return new YamlResourceImpl(uri, loader, context, progressMonitor);
	}
	
}
