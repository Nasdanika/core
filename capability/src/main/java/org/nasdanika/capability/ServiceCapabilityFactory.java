package org.nasdanika.capability;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;

import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

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

	public abstract boolean isFor(Class<?> type, Object serviceRequirement);
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<Iterable<CapabilityProvider<S>>> create(
			Object requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		if (requirement instanceof Class) {
			requirement = new Requirement<R,S>((Class<S>) requirement, null, null);
		}
		
		if (requirement instanceof Requirement) {
			Requirement<R,S> theRequirement = (Requirement<R,S>) requirement;
			if (isFor(theRequirement.serviceType(), theRequirement.serviceRequirement())
					&& (theRequirement.factoryPredicate() == null || theRequirement.factoryPredicate().test(this))) {
				// TODO - split progress monitor
				return createService(theRequirement.serviceType(), theRequirement.serviceRequirement(), loader, progressMonitor);
			}
		} 
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
		
	protected abstract CompletionStage<Iterable<CapabilityProvider<S>>> createService(
		Class<S> serviceType,	
		R serviceRequirement,
		Loader loader,
		ProgressMonitor progressMonitor);	
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<S>>> wrap(S service) {
		return wrapCompletionStage(service == null ? null : CompletableFuture.completedStage(service));
	}

	/**
	 * @return Completed stage with no providers.
	 */
	protected CompletionStage<Iterable<CapabilityProvider<S>>> empty() {
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<S>>> wrapCompletionStage(CompletionStage<S> serviceCompletionStage) {
		if (serviceCompletionStage == null) {
			return CompletableFuture.completedStage(Collections.singleton(new CapabilityProvider<S>() {
				
				@Override
				public Flux<S> getPublisher() {
					return Flux.empty();
				}
				
			}));		
		}
		
		return serviceCompletionStage.thenApply(service -> Collections.singleton(new CapabilityProvider<S>() {
			
			@Override
			public Flux<S> getPublisher() {
				return service == null ? Flux.empty() : Flux.just(service);
			}
			
		}));		
	}
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<S>>> wrapError(Throwable error) {
		CapabilityProvider<S> capabilityProvider = new CapabilityProvider<S>() {

			@Override
			public Flux<S> getPublisher() {
				return Flux.error(error);
			}
			
		};
		return CompletableFuture.completedStage(Collections.singleton(capabilityProvider));		
	}
	
}
