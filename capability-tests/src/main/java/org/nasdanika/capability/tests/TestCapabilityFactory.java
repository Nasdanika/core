package org.nasdanika.capability.tests;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * 
 */
public class TestCapabilityFactory implements CapabilityFactory {
	
	public record Requirement(String value){};

	@Override
	public CompletionStage<Iterable<CapabilityProvider<?>>> create(Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		if (requirement instanceof Requirement) {

			return resolver.apply(MyService.class, progressMonitor).thenApply(cp -> {;
				@SuppressWarnings("unchecked")
				Flux<MyService> myServiceCapabilityPublisher = (Flux<MyService>) cp.iterator().next().getPublisher();
				
				return Collections.singleton(new CapabilityProvider<Object>() {
		
					@Override
					public Flux<Object> getPublisher() {
						return myServiceCapabilityPublisher.map(ms -> ms.count(((Requirement) requirement).value()));
					}
					
				});
			});
			
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}

}
