package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.SupercedingCapabilityProvider;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Uses the capability framework to create processors
 * @param <R> Processor requirement to be used by capability factories 
 * @param <P> Processor type
 */
public class CapabilityProcessorFactory<R,H,E,K,P> extends ProcessorFactory<H,E,K,P> {
	
	private CapabilityLoader capabilityLoader;
	private R requirement;
	private Class<?> processorType;
	private Class<?> handlerType;
	private Class<?> endpointType;

	public static record ProcessorRequirement<R,H,E,K,P>(
			ProcessorConfig<H,E,K> config, 
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,K,P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			Class<?> handlerType,
			Class<?> endpointType,
			R requirement) {}
	
	public CapabilityProcessorFactory(
			Class<?> processorType,
			Class<?> handlerType,
			Class<?> endpointType,
			R requirement, 
			CapabilityLoader capabilityLoader) {
		
		this.processorType = processorType;
		this.handlerType = handlerType;
		this.endpointType = endpointType;
		this.requirement = requirement;
		this.capabilityLoader = capabilityLoader;		
	}
	
	@Override
	protected ProcessorInfo<H,E,K,P> createProcessor(
		ProcessorConfig<H,E,K> config, 
		boolean parallel,
		BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,K,P>, ProgressMonitor>> infoProvider,
		Consumer<CompletionStage<?>> endpointWiringStageConsumer, ProgressMonitor progressMonitor) {
		
		ProcessorRequirement<R,H,E,K,P> processorRequirement = new ProcessorRequirement<>(
				config, 
				infoProvider, 
				endpointWiringStageConsumer,
				handlerType,
				endpointType,
				requirement);
		
		Requirement<ProcessorRequirement<R,H,E,K,P>, ?> serviceRequirement = ServiceCapabilityFactory.createRequirement(processorType, this::testFactory, processorRequirement);
		Iterable<CapabilityProvider<Object>> providers = capabilityLoader.load(serviceRequirement, progressMonitor);
		return ProcessorInfo.of(config, select(providers));
	}

	/**
	 * Selects one processor from providers. E.g. more specific.
	 * This implementation returns the first non-null processor
	 * @param processors
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected P select(Iterable<CapabilityProvider<Object>> providers) {
		Collection<CapabilityProvider<Object>> accumulator = new ArrayList<>();
		Z: for (CapabilityProvider<Object> provider: providers) {
			Iterator<CapabilityProvider<Object>> ait = accumulator.iterator();
			while (ait.hasNext()) {
				CapabilityProvider<Object> accumulatedProvider = ait.next();
				if (accumulatedProvider instanceof SupercedingCapabilityProvider && ((SupercedingCapabilityProvider<?>) accumulatedProvider).supercedes(provider)) {
					continue Z;
				}
				if (provider instanceof SupercedingCapabilityProvider && ((SupercedingCapabilityProvider<?>) provider).supercedes(accumulatedProvider)) {
					ait.remove();
				}
			}
			accumulator.add(provider);
		}
		for (CapabilityProvider<Object> provider: accumulator) {
			Collection<Object> processors = new ArrayList<>();
			provider.getPublisher().filter(Objects::nonNull).subscribe(processors::add);
			if (!processors.isEmpty()) {
				return (P) processors.iterator().next();
			}
		}
		return null;
	}
	
	protected boolean testFactory(ServiceCapabilityFactory<ProcessorRequirement<R,H,E,K,P>,?> factory) {
		return true;		
	}

}
