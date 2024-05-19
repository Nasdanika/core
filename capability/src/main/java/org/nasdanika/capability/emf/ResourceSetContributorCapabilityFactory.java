package org.nasdanika.capability.emf;

import java.util.function.Predicate;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.capability.ServiceCapabilityFactory;

/**
 * Base class for {@link EPackage} factories
 */
public abstract class ResourceSetContributorCapabilityFactory extends ServiceCapabilityFactory<Predicate<ResourceSetContributor>, ResourceSetContributor> {
	
	@Override
	public boolean isForServiceType(Class<?> type) {
		return ResourceSetContributor.class == type;
	}
	
}
