package org.nasdanika.capability.tests;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * Creates a list containing a double for a double requirement. 
 */
public class ListFactory extends ServiceCapabilityFactory<Double, List<Double>> {

	@Override
	public boolean isForServiceType(Class<?> type) {
		return List.class.equals(type);
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<List<Double>>>> createService(
			Class<List<Double>> serviceType,
			Double serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {

		return CompletableFuture.completedStage(Collections.singleton(new CapabilityProvider<List<Double>>() {
			
			@Override
			public Flux<List<Double>> getPublisher() {
				return Flux.just(Collections.singletonList(serviceRequirement));
			}
			
		}));
	}

}
