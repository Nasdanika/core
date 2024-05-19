package org.nasdanika.capability.emf;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.common.ProgressMonitor;

/**
 * Service/capability interface for contributors to {@link ResourceSet}. 
 * Implementations may register {@link EPackage}s, resource factories, adapter factories, {@link URIHandler}'s, ...
 */
public interface ResourceSetContributor {
	
	void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor);

}
