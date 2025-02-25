package org.nasdanika.maven;

import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.emf.URIConverterContributorCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Contributes {@link URIHandler} loading from Maven repositories
 */
public class MavenURIConverterContributorCapabilityFactory extends URIConverterContributorCapabilityFactory {

	@Override
	protected void contribute(
			URIConverter uriConverter, 
			Loader loader,
			ProgressMonitor progressMonitor) {	
		uriConverter.getURIHandlers().add(0, new MavenURIHandler(loader.getCapabilityLoader(), progressMonitor));
		
	}
	
}
