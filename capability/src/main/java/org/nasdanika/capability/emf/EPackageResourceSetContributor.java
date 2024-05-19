package org.nasdanika.capability.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.ProgressMonitor;

public interface EPackageResourceSetContributor extends ResourceSetContributor {
	
	EPackage getEPackage();
	
	URI getDocumentationURI();
	
	@Override
	default void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		EPackage ePackage = getEPackage();
		resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);		
	}

}
