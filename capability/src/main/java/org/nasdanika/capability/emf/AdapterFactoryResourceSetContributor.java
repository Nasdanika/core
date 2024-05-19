package org.nasdanika.capability.emf;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.ProgressMonitor;

public interface AdapterFactoryResourceSetContributor extends ResourceSetContributor {
	
	AdapterFactory getAdapterFactory();
	
	@Override
	default void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor) {		
		resourceSet.getAdapterFactories().add(getAdapterFactory());
	}

}
