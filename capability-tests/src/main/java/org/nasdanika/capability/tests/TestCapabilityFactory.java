package org.nasdanika.capability.tests;

import java.util.Collections;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * 
 */
public class TestCapabilityFactory implements CapabilityFactory<TestCapabilityFactory.Requirement, Integer> {
	
	public record Requirement(String value){};
	
	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof Requirement;
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<Integer>>> create(
			Requirement requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		return resolver.apply(MyService.class, progressMonitor).thenApply(cp -> {;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Flux<MyService> myServiceCapabilityPublisher = (Flux) cp.iterator().next().getPublisher();
			
			return Collections.singleton(new CapabilityProvider<Integer>() {
	
				@Override
				public Flux<Integer> getPublisher() {
					Function<MyService, Integer> mapper = ms -> ms.count(((Requirement) requirement).value());
					return myServiceCapabilityPublisher.map(mapper);
				}
				
			});
		});
	}

}
