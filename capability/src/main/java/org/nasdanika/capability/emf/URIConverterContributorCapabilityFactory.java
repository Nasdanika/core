package org.nasdanika.capability.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for {@link EPackage} factories
 */
public abstract class URIConverterContributorCapabilityFactory extends ResourceSetContributorCapabilityFactory {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSetContributor>>> createService(
			Class<ResourceSetContributor> serviceType,
			Predicate<ResourceSetContributor> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		URIConverterResourceSetContributor contributor = new URIConverterResourceSetContributor() {

			@Override
			public void contribute(URIConverter uriConverter, ProgressMonitor progressMonitor) {
				URIConverterContributorCapabilityFactory.this.contribute(uriConverter, progressMonitor);				
			}
			
		};
		
		if (serviceRequirement == null || serviceRequirement.test(contributor)) {
			return wrap(contributor);
		}
		
		return empty();
	}
	
	protected abstract void contribute(URIConverter uriConverter, ProgressMonitor progressMonitor);

}
