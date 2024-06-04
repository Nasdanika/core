package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.CapabilityProcessorFactory.ProcessorRequirement;

import reactor.core.publisher.Flux;


/**
 * Creates graph element processors by reflectively calling factory objects with annotated methods.
 * @param <R>
 * @param <P>
 */
public abstract class ReflectiveProcessorServiceFactory<R,P> extends ServiceCapabilityFactory<ProcessorRequirement<R, P>, P> {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<P>>> createService(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		CompletionStage<Collection<Object>> collectorCS = CompletableFuture.completedStage(new ArrayList<Object>());
		for (CompletionStage<Object> targetCS: loadTargets(processorType, serviceRequirement, resolver, progressMonitor)) {
			collectorCS = collectorCS.thenCombine(targetCS, (collector, target) -> {
				collector.add(target);
				return collector;
			});
		}
		
		return collectorCS
				.thenApply(targets -> createProcessor(processorType, serviceRequirement, resolver, targets, progressMonitor))
				.thenApply(this::wrapProcessor);
	}
	
	protected Iterable<CapabilityProvider<P>> wrapProcessor(P processor) {
		return Collections.singleton(new CapabilityProvider<P>() {
			
			@Override
			public Flux<P> getPublisher() {
				return Flux.just(processor);
			}
			
		});		
	}
	
	protected P createProcessor(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			Collection<Object> targets, 
			ProgressMonitor progressMonitor) {
		
		// TODO - reflector, find a factory method.
		return null;
	}
	
	/**
	 * Loads/creates reflection targets.
	 * @param processorType
	 * @param serviceRequirement
	 * @param resolver
	 * @param progressMonitor
	 * @return
	 */
	protected abstract Iterable<CompletionStage<Object>> loadTargets(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor);

}
