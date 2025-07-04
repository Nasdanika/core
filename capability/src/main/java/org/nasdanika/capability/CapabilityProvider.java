package org.nasdanika.capability;

import java.util.Objects;

import reactor.core.publisher.Flux;

/**
 * Base interface for capability providers. 
 * Specializations may provide information about capability.
 * For example, quality attributes.  
 */
public interface CapabilityProvider<T> {
	
	/**
	 * @return Publisher of provided capabilities 
	 */
	Flux<T> getPublisher();
	
	/**
	 * Wraps capabilities into a capability provider
	 * @param <T>
	 * @param capabilities
	 * @return
	 */
	@SafeVarargs
	static <T> CapabilityProvider<T> of(T... capabilities) {
		return new CapabilityProvider<T>() {

			@Override
			public Flux<T> getPublisher() {
				return Flux.just(capabilities).filter(Objects::nonNull);
			}
		};
	}
	
	static <T> CapabilityProvider<T> ofError(Throwable error) {
		return new CapabilityProvider<T>() {
	
				@Override
				public Flux<T> getPublisher() {
					return Flux.error(error);
				}
				
		};
	}

}
