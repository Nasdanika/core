package org.nasdanika.maven;

import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

public class MavenURIHandlerFactory extends ServiceCapabilityFactory<Void, URIHandler> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return URIHandler.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<URIHandler>>> createService(
			Class<URIHandler> serviceType,
			Void serviceRequirement, 
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		return wrap(new MavenURIHandler(loader.getCapabilityLoader(), progressMonitor));
	}
	
}