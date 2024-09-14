package org.nasdanika.capability;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import reactor.core.publisher.Flux;

/**
 * Base class with helper methods
 */
public abstract class AbstractCapabilityFactory<R,C> implements CapabilityFactory<R, C> {
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<C>>> wrap(C capability) {
		return wrapCompletionStage(capability == null ? null : CompletableFuture.completedStage(capability));
	}

	/**
	 * @return Completed stage with no providers.
	 */
	protected CompletionStage<Iterable<CapabilityProvider<C>>> empty() {
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<C>>> wrapCompletionStage(CompletionStage<C> capabilityCompletionStage) {
		if (capabilityCompletionStage == null) {
			return CompletableFuture.completedStage(Collections.singleton(new CapabilityProvider<C>() {
				
				@Override
				public Flux<C> getPublisher() {
					return Flux.empty();
				}
				
			}));		
		}
		
		return capabilityCompletionStage.thenApply(service -> Collections.singleton(new CapabilityProvider<C>() {
			
			@Override
			public Flux<C> getPublisher() {
				return service == null ? Flux.empty() : Flux.just(service);
			}
			
		}));		
	}
	
	/**
	 * Helper method for factories with no dependencies providing a single instance of a service.
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<C>>> wrapError(Throwable error) {
		CapabilityProvider<C> capabilityProvider = new CapabilityProvider<C>() {

			@Override
			public Flux<C> getPublisher() {
				return Flux.error(error);
			}
			
		};
		return CompletableFuture.completedStage(Collections.singleton(capabilityProvider));		
	}
	

}
