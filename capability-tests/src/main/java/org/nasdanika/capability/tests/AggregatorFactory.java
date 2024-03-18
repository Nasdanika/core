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
public class AggregatorFactory implements CapabilityFactory {
	
	public record Requirement(String value){};

	@Override
	public CompletionStage<Iterable<CapabilityProvider<?>>> create(Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		if (requirement instanceof Requirement) {
			String reqValue = ((Requirement) requirement).value();
			
			CompletionStage<Iterable<CapabilityProvider<?>>> myServiceProviders = resolver.apply(MyService.class, progressMonitor);
			CompletionStage<Iterable<CapabilityProvider<?>>> testProviders = resolver.apply(new TestCapabilityFactory.Requirement(reqValue) , progressMonitor);
			
			return myServiceProviders.thenCombine(testProviders, this::combine);
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	protected Iterable<CapabilityProvider<?>> combine(Iterable<CapabilityProvider<?>> myServiceProviders, Iterable<CapabilityProvider<?>> testProviders) {
		@SuppressWarnings("unchecked")
		Flux<MyService> myServiceCapabilityPublisher = (Flux<MyService>) myServiceProviders.iterator().next().getPublisher();
		
		return Collections.singleton(new CapabilityProvider<Object>() {

			@Override
			public Flux<Object> getPublisher() {
				return myServiceCapabilityPublisher.map(ms -> {
					return "Some result: " + testProviders;
				});
			}
			
		});
	}

}
