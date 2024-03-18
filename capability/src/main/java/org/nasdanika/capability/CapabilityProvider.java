package org.nasdanika.capability;

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

}
