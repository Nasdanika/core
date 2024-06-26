package org.nasdanika.capability.emf;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Requirement for a {@link ResourceSet}.
 * @param resourceSet an optional instance of {@link ResourceSet} to configure.
 * @param configurator resource set is passed to the configurator if the configurator is not null.
 * @param contributorPredicate allows to filter contributors, e.g. register only certain {@link EPackage}s.
 */
public record ResourceSetRequirement(
		ResourceSet resourceSet,
		Consumer<ResourceSet> configurator,
		Predicate<ResourceSetContributor> contributorPredicate) {
	
}
