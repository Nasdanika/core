package org.nasdanika.capability.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for {@link EPackage} factories
 */
public abstract class ResourceFactoryCapabilityFactory extends ResourceSetContributorCapabilityFactory {
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSetContributor>>> createService(
			Class<ResourceSetContributor> serviceType,
			Predicate<ResourceSetContributor> serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {

		ResourceFactoryResourceSetContributor contributor = new ResourceFactoryResourceSetContributor() {

			@Override
			public Factory getResourceFactory(ResourceSet resourceSet) {
				return ResourceFactoryCapabilityFactory.this.getResourceFactory(resourceSet, loader, progressMonitor);
			}

			@Override
			public String getContentType() {
				return ResourceFactoryCapabilityFactory.this.getContentType();
			}

			@Override
			public String getExtension() {
				return ResourceFactoryCapabilityFactory.this.getExtension();
			}

			@Override
			public String getProtocol() {
				return ResourceFactoryCapabilityFactory.this.getProtocol();
			}
		};
		
		if (serviceRequirement == null || serviceRequirement.test(contributor)) {
			return wrap(contributor);
		}
		
		return empty();
	}
		
	protected abstract Resource.Factory getResourceFactory(
			ResourceSet resourceSet, 
			Loader loader,
			ProgressMonitor progressMonitor);
	
	protected String getContentType() {
		return null;
	}; 
	
	protected String getExtension() {
		return null;
	}
	
	protected String getProtocol() {
		return null;
	}

}
