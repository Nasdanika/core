package org.nasdanika.capability;

import java.util.List;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

/**
 * Classes providing a service requirement can implement this interface for 
 * loadXXX convenience methods.
 * @param <R>
 * @param <S>
 */
public interface ServiceRequirementProvider<R,S> {
	
	ServiceCapabilityFactory.Requirement<R,S> getServiceRequirement(); 
	
	default S loadOne(CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		return capabilityLoader.loadOne(getServiceRequirement(), progressMonitor);
	}
	
	default Iterable<CapabilityProvider<S>> load(CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		return capabilityLoader.load(getServiceRequirement(), progressMonitor);
	}
	
	default List<S> loadAll(CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		return capabilityLoader.loadAll(getServiceRequirement(), progressMonitor);
	}
	
	default CompletionStage<S> loadOne(CapabilityFactory.Loader loader, ProgressMonitor progressMonitor) {
		return loader.loadOne(getServiceRequirement(), progressMonitor);
	}
	
	default CompletionStage<Iterable<CapabilityProvider<S>>> load(CapabilityFactory.Loader loader, ProgressMonitor progressMonitor) {
		return loader.load(getServiceRequirement(), progressMonitor);
	}
	
	default CompletionStage<List<S>> loadAll(CapabilityFactory.Loader loader, ProgressMonitor progressMonitor) {
		return loader.loadAll(getServiceRequirement(), progressMonitor);
	}
	
}
