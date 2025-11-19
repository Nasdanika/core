package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.CapabilityProcessorFactory.ProcessorRequirement;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;

import reactor.core.publisher.Flux;

/**
 * Creates graph element processors using {@link ReflectiveProcessorFactoryProvider} which calls targets provided for {@link ReflectiveProcessorFactoryProviderTargetRequirement}.
 * @param <R>
 * @param <P>
 */
public class ReflectiveProcessorServiceFactory<R,H,E,P> extends ServiceCapabilityFactory<ProcessorRequirement<R,H,E,P>, P> {
	
	/**
	 * Requirement for a {@link ReflectiveProcessorFactoryProvider} target
	 * @param <R>
	 * @param <P>
	 */
	public static record ReflectiveProcessorFactoryProviderTargetRequirement<R,H,E,P>(
			Class<P> processorType,
			ProcessorRequirement<R,H,E,P> processorRequirement) {}	
	
	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return serviceRequirement instanceof ProcessorRequirement;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<P>>> createService(
			Class<P> processorType,
			ProcessorRequirement<R,H,E,P> serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		ReflectiveProcessorFactoryProviderTargetRequirement<R,H,E,P> targetRequirement = new ReflectiveProcessorFactoryProviderTargetRequirement<>(processorType, serviceRequirement);		
		CompletionStage<Iterable<CapabilityProvider<Object>>> targetsCS = loader.load(targetRequirement, progressMonitor);
		return targetsCS.thenApply(targets -> createProcessors(processorType, serviceRequirement, loader, targets, progressMonitor));
	}
	
	protected Iterable<CapabilityProvider<P>> createProcessors(
			Class<P> processorType,
			ProcessorRequirement<R,H,E,P> serviceRequirement,
			Loader loader,
			Iterable<CapabilityProvider<Object>> targetCapabilityProviders, 
			ProgressMonitor progressMonitor) {
				
		Collection<Object> targets = new ArrayList<>();
		
		for (CapabilityProvider<Object> tcp: targetCapabilityProviders) {
			tcp.getPublisher().filter(Objects::nonNull).collectList().block().forEach(targets::add);
		}
		
		ReflectiveProcessorFactoryProvider<H,E,P> rpfp = new ReflectiveProcessorFactoryProvider<>(targets.toArray()) {
			
			@Override
			protected void onEvaluationException(Object obj, String expr, EvaluationContext evaluationContext, EvaluationException exception) {
//				System.err.println("---");
//				System.err.println(expr);
//				exception.printStackTrace();
			}
			
		};
		
		P processor = rpfp.createProcessor(
				serviceRequirement.config(),
				isParallel(),
				serviceRequirement.infoProvider(),
				serviceRequirement.endpointWiringStageConsumer(),
				progressMonitor);
		
		if (processor == null) {
			return Collections.emptyList();
		}
				
		return Collections.singleton(new CapabilityProvider<P>() {

			@Override
			public Flux<P> getPublisher() {
				return Flux.just(processor);
			}
			
		});
	}
	
	/**
	 * Override to return true to perform wiring in multiple threads.
	 * @return
	 */
	protected boolean isParallel() {
		return false;
	}
	
}
