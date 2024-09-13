package org.nasdanika.capability.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for {@link EPackage} factories
 */
public abstract class AdapterFactoryCapabilityFactory extends ResourceSetContributorCapabilityFactory {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSetContributor>>> createService(
			Class<ResourceSetContributor> serviceType,
			Predicate<ResourceSetContributor> serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {

		AdapterFactoryResourceSetContributor contributor = () -> getAdapterFactory();
		
		if (serviceRequirement == null || serviceRequirement.test(contributor)) {
			return wrap(contributor);
		}
		
		return empty();
	}
	
	protected abstract AdapterFactory getAdapterFactory();

}
