package org.nasdanika.capability.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;

public class ContentsHandlingResourceFactory extends ResourceFactoryImpl {
	
	protected CapabilityLoader capabilityLoader;
	private ProgressMonitor progressMonitor;
	
	public ContentsHandlingResourceFactory(CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		this.capabilityLoader = capabilityLoader;
		this.progressMonitor = progressMonitor;
	}
		
	@Override
	public Resource createResource(URI uri) {
		return new ContentsHandlingResource(uri, capabilityLoader, progressMonitor);
	}
			
}
