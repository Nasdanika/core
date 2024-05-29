package org.nasdanika.capability.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Creates and configures {@link ResourceSet} according to the {@link ResourceSetRequirement}
 */
public class ResourceSetCapabilityFactory extends ServiceCapabilityFactory<ResourceSetRequirement, ResourceSet> {

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return ResourceSet.class == type;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSet>>> createService(
			Class<ResourceSet> serviceType,
			ResourceSetRequirement serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		Requirement<Predicate<ResourceSetContributor>, ResourceSetContributor> contributorRequirement = ServiceCapabilityFactory.createRequirement(
				ResourceSetContributor.class, 
				null, 
				serviceRequirement == null ? null : serviceRequirement.contributorPredicate());
		CompletionStage<Iterable<CapabilityProvider<Object>>> contributorsCS = resolver.apply(contributorRequirement, progressMonitor);
		CompletionStage<ResourceSet> rscs = contributorsCS.thenApply(cp -> contribute(serviceRequirement, cp, progressMonitor));
		return wrapCompletionStage(rscs);
	}
	
	protected ResourceSet contribute(
			ResourceSetRequirement requirement, 
			Iterable<CapabilityProvider<Object>> contributorProviders, 
			ProgressMonitor progressMonitor) {
		
		ResourceSet resourceSet = createResourceSet(requirement);
		for (CapabilityProvider<Object> cp: contributorProviders) {
			cp.getPublisher().subscribe(contributor -> ((ResourceSetContributor) contributor).contribute(resourceSet, progressMonitor));
		}
		Consumer<ResourceSet> configurator = requirement.configurator();
		if (configurator != null) {
			configurator.accept(resourceSet);
		}
				
		return resourceSet;		
	}
	
	protected ResourceSet createResourceSet(ResourceSetRequirement requirement) {
		if (requirement != null) {
			ResourceSet ret = requirement.resourceSet();
			if (ret != null) {
				return ret;
			}
		}
		return new ResourceSetImpl();
	}

}
