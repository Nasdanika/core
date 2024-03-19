package org.nasdanika.capability;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.nasdanika.common.ProgressMonitor;

public interface ServiceCapabilityFactory<R,S> extends CapabilityFactory<Object,S> {
		
	/**
	 * Service requirement. factoryPredicate is applied to the factory instance. 
	 * Service requirement is passed to the factory.
	 * @param <T> Service type
	 */
	record Requirement<R,S>(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement) {}
	
	static <R,S> Requirement<R,S> createRequirement(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement) {
		return new Requirement<R, S>(serviceType, factoryPredicate, serviceRequirement);
	}
	
	@Override
	default boolean canHandle(Object requirement) {
		return requirement instanceof Class || requirement instanceof Requirement;
	}

	boolean isForServiceType(Class<?> type);
	
	@SuppressWarnings("unchecked")
	@Override
	default CompletionStage<Iterable<CapabilityProvider<S>>> create(
			Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		if (requirement instanceof Class) {
			requirement = new Requirement<R,S>((Class<S>) requirement, null, null);
		}
		
		if (requirement instanceof Requirement) {
			Requirement<R,S> theRequirement = (Requirement<R,S>) requirement;
			if (isForServiceType(theRequirement.serviceType())
					&& (theRequirement.factoryPredicate() == null || theRequirement.factoryPredicate().test(this))) {
				// TODO - split progress monitor
				return createService(theRequirement.serviceRequirement(), resolver, progressMonitor);
			}
		} 
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
		
	CompletionStage<Iterable<CapabilityProvider<S>>> createService(
				R serviceRequirement,
				BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
				ProgressMonitor progressMonitor);	
	
}
