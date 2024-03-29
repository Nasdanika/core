package org.nasdanika.capability;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.nasdanika.common.ProgressMonitor;

public abstract class ServiceCapabilityFactory<R,S> implements CapabilityFactory<Object,S> {
		
	/**
	 * Service requirement. factoryPredicate is applied to the factory instance. 
	 * Service requirement is passed to the factory.
	 * @param <T> Service type
	 */
	public record Requirement<R,S>(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement) {}
	
	public static <R,S> Requirement<R,S> createRequirement(Class<S> serviceType) {
		return createRequirement(serviceType, null, null);
	}
	
	public static <R,S> Requirement<R,S> createRequirement(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement) {
		return new Requirement<R, S>(serviceType, factoryPredicate, serviceRequirement);
	}
	
	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof Class || requirement instanceof Requirement;
	}

	public abstract boolean isForServiceType(Class<?> type);
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<Iterable<CapabilityProvider<S>>> create(
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
				return createService(theRequirement.serviceType(), theRequirement.serviceRequirement(), resolver, progressMonitor);
			}
		} 
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
		
	protected abstract CompletionStage<Iterable<CapabilityProvider<S>>> createService(
		Class<S> serviceType,	
		R serviceRequirement,
		BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
		ProgressMonitor progressMonitor);	
	
}
