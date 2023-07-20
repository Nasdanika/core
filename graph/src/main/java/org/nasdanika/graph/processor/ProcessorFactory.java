package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		
	public Map<Element, ProcessorRecord<P>> createProcessors(Map<Element, ProcessorConfig> configs, boolean parallel, ProgressMonitor progressMonitor) {
		record ProcessorCompletableFutureRecord<P>(ProcessorConfig config, CompletableFuture<P> processorCompletableFuture) {}
		Map<Element, ProcessorCompletableFutureRecord<P>> processors = Collections.synchronizedMap(new LinkedHashMap<>());
		configs.entrySet().forEach(e -> processors.put(e.getKey(), new ProcessorCompletableFutureRecord<P>(e.getValue(), new CompletableFuture<P>())));
		Stream<ProcessorCompletableFutureRecord<P>> recordStream = processors.values().stream();
		if (parallel) {
			recordStream = recordStream.parallel();
		}
		List<CompletionStage<?>> stages = Collections.synchronizedList(new ArrayList<>());
		Function<Element, CompletionStage<P>> processorProvider = e -> {
			ProcessorCompletableFutureRecord<P> rec = processors.get(e);
			return rec == null ? null : rec.processorCompletableFuture();
		};
		recordStream.forEach(r -> r.processorCompletableFuture().complete(createProcessor(r.config(), parallel, processorProvider, stages::add, progressMonitor)));
		
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
		
		Map<Element, ProcessorRecord<P>> ret = new LinkedHashMap<>();
		processors.forEach((e, r) -> ret.put(e, new ProcessorRecord<P>(r.config(), r.processorCompletableFuture().join())));
		return ret;		
	}

}
