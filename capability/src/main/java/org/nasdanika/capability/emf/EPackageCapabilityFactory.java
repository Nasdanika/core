package org.nasdanika.capability.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for {@link EPackage} factories
 */
public abstract class EPackageCapabilityFactory extends ResourceSetContributorCapabilityFactory {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSetContributor>>> createService(
			Class<ResourceSetContributor> serviceType,
			Predicate<ResourceSetContributor> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {

		EPackageResourceSetContributor contributor = new EPackageResourceSetContributor() {
			
			@Override
			public EPackage getEPackage() {
				return EPackageCapabilityFactory.this.getEPackage();
			}
			
			@Override
			public URI getDocumentationURI() {
				return EPackageCapabilityFactory.this.getDocumentationURI();
			}
		};
		
		if (serviceRequirement == null || serviceRequirement.test(contributor)) {
			return wrap(contributor);
		}
		
		return empty();
	}
	
	protected abstract EPackage getEPackage();
	
	protected abstract URI getDocumentationURI();

}
