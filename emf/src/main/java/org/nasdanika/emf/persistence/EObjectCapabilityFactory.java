package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.EObjectCapabilityFactory.EObjectRequirement;
import org.nasdanika.ncore.util.NcoreResourceSet;

/**
 * Loads {@link RuleSet}. Requirement is RuleSet ID 
 */
public class EObjectCapabilityFactory extends ObjectLoaderCapabilityFactory<EObjectRequirement, EObject> {
	
	public record EObjectRequirement(URI uri, ResourceSetRequirement resourceSetRequirement) {} 
	
	public static EObjectRequirement createRequirement(URI uri, ResourceSetRequirement resourceSetRequirement) {
		return new EObjectRequirement(uri, resourceSetRequirement);
	}
	
	public static EObjectRequirement createRequirement(URI uri) {
		return createRequirement(uri, null);
	}

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return EObject.class == type && requirement instanceof EObjectRequirement;
	}

	@Override
	protected EObject load(EObjectRequirement requirement, ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		return resourceSet.getEObject(requirement.uri(), true);
	}
	
	@Override
	protected ResourceSetRequirement getResourceSetRequirement(EObjectRequirement serviceRequirement) {
		ResourceSetRequirement resourceSetRequirement = serviceRequirement.resourceSetRequirement();		
		return resourceSetRequirement == null ? new ResourceSetRequirement(new NcoreResourceSet(), null, null) : resourceSetRequirement;
	}

}
