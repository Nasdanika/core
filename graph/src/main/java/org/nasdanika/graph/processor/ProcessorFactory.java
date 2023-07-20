package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Processor factory creates and "wires" processors.
 * @author Pavel
 *
 * @param <P> Processor type - what is passed to parent/child processors and also returned to the client code to interact with the processor network. For model elements which are not interacted with by other processors or the client code the result may be null.
 * @param <H> Handler type. Handlers are invoked by endpoints. 
 * @param <E> Endpoint type. Endpoints pass invocations to handlers.
 * @param <R> Registry type
 */
public abstract class ProcessorFactory<P> {
	
	/**
	 * Creates a processor
	 * @param config Processor config
	 * @param parallel If true, creation may be performed in parallel threads
	 * @param processorProvider Provider of element processors
	 * @param stageConsumer Consumer of stages which might be completed after this method returns, e.g. wiring of dependencies. 
	 * @param progressMonitor
	 * @return
	 */
	protected abstract P createProcessor(
			ProcessorConfig config, 
			boolean parallel, 
			Function<Element,CompletionStage<P>> processorProvider,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor);
		
	public Map<Element, P> createProcessors(Map<Element, ProcessorConfig> configs, boolean parallel, ProgressMonitor progressMonitor) {
		Map<Element, CompletableFuture<P>> processors = Collections.synchronizedMap(new LinkedHashMap<>());
		configs.keySet().forEach(e -> processors.put(e, new CompletableFuture<P>()));
		Stream<Entry<Element, ProcessorConfig>> configEntryStream = configs.entrySet().stream();
		if (parallel) {
			configEntryStream = configEntryStream.parallel();
		}
		List<CompletionStage<?>> stages = Collections.synchronizedList(new ArrayList<>());
		configEntryStream.forEach(ce -> {
			processors.get(ce.getKey()).complete(createProcessor(ce.getValue(), parallel, processors::get, stages::add, progressMonitor));
		});
		
		// Collecting exceptions
		CompletableFuture<?>[] toCompleteArray = stages.stream().map(CompletionStage::toCompletableFuture).filter(CompletableFuture::isCompletedExceptionally).collect(Collectors.toList()).toArray(new CompletableFuture[0]);
		
		CompletableFuture.allOf(toCompleteArray).handle((r, e) -> {
			if (e == null) {
				return null;
			}
			NasdanikaException ne = new NasdanikaException("Theres's been errors during processor creation");
			for (CompletableFuture<?> cf: toCompleteArray) {
				if (cf.isCompletedExceptionally()) {
					cf.whenComplete((rs, ex) -> ne.addSuppressed(ex));
				}
			}
			throw ne;
		}).join();
		
		Map<Element, P> ret = new LinkedHashMap<>();
		processors.forEach((e, pcf) -> ret.put(e, pcf.join()));
		return ret;		
	}

}
