package org.nasdanika.cli;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetContributor;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.ProgressMonitor;

/**
 * Mixes in a {@link ResourceSet} loaded by {@link CapabilityLoader}.
 * @author Pavel
 *
 */
public class ResourceSetMixIn {

	// TODO - includes/excludes for epackages, resource and adapter factories. Resource factories - maps - extensions, protocols, content types 
//	@Option(names = {"-p", "--progress"}, description = "Output file for progress monitor")
//	private File progressOutput;
//	
//	@Option(names = {"-j", "--json"}, description = "Output progress in JSON")
//	private boolean jsonProgress;
//	
//	@Option(names = {"-d", "--data"}, description = "Output progress data")
//	private boolean data;
		
	public ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		CapabilityLoader capabilityLoader = getCapabilityLoader();
		ResourceSetRequirement serviceRequirement = new ResourceSetRequirement(createRequirementResourceSet(), this::testContributor);		
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class, this::testFactory, serviceRequirement);		
		for (CapabilityProvider<?> cp: capabilityLoader.load(requirement, progressMonitor)) {
			return configureResourceSet((ResourceSet) cp.getPublisher().blockFirst());
		}	
		
		return configureResourceSet(createDefaultResourceSet()); // Fall-back if there are no capability providers.
	}
	
	protected ResourceSet configureResourceSet(ResourceSet resourceSet) {
		// TODO - explicit registrations
		return resourceSet;
	}

	protected ResourceSetImpl createDefaultResourceSet() {
		return new ResourceSetImpl();
	}

	protected CapabilityLoader getCapabilityLoader() {
		return new CapabilityLoader();
	}	
	
	/**
	 * Override to customize {@link ResourceSet} implementation type. This implementation returns null. 
	 * @return {@link ResourceSet} instance to pass to {@link ResourceSetRequirement}. 
	 */
	protected ResourceSet createRequirementResourceSet() {
		return null;
	}
		
	protected boolean testContributor(ResourceSetContributor contributor) {
		return true; // TODO - include/exclude epackages, resource and adapter factories
	}
	
	protected boolean testFactory(ServiceCapabilityFactory<ResourceSetRequirement, ResourceSet> factory) {
		return true;
	}

}
