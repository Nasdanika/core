package org.nasdanika.capability.emf;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.nasdanika.common.ProgressMonitor;

public interface URIConverterResourceSetContributor extends ResourceSetContributor {
	
	@Override
	default void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		contribute(resourceSet.getURIConverter(), progressMonitor);
	}
	
	void contribute(URIConverter uriConverter, ProgressMonitor progressMonitor);

}
