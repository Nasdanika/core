package org.nasdanika.maven;

import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.emf.URIConverterContributorCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Contributes {@link URIHandler} loading from classpath schema
 */
public class MavenURIHandlerCapabilityFactory extends URIConverterContributorCapabilityFactory {

	@Override
	protected void contribute(
			URIConverter uriConverter, 
			Loader loader,
			ProgressMonitor progressMonitor) {	
		uriConverter.getURIHandlers().add(0, new MavenbURIHandler(loader.getCapabilityLoader(), progressMonitor));
		
	}
	
}
