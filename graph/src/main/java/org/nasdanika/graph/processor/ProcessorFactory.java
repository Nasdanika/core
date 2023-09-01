package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
	 * @param infoProvider Provider of element info
	 * @param endpointWiringStageConsumer Consumer of endpoint wiring completion stages. It is used to collect wiring exceptions. 
	 * @param progressMonitor
	 * @return
	 */
	protected ProcessorInfo<P> createProcessor(
			ProcessorConfig config, 
			boolean parallel, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor) {
		
		return ProcessorInfo.of(config, null);
	};
	
	/**
	 * Handles exceptionally completed stages
	 * @param throwed
	 */
	protected void onExceptionalCompletions(List<Throwable> throwed) {
		if (!throwed.isEmpty()) {
			NasdanikaException ne = new NasdanikaException("Theres's been errors during processor creation");
			for (Throwable th: throwed) {
				ne.addSuppressed(th);
			}
			throw ne;
		}
	}

	/**
	 * Joins stages
	 * @param stages
	 */
	protected void join(List<CompletableFuture<?>> stages) {
		stages
			.stream()
			.map(CompletionStage::toCompletableFuture)
			.map(CompletableFuture::join);
	}
		
	public Map<Element, ProcessorInfo<P>> createProcessors(Collection<ProcessorConfig> configs, boolean parallel, ProgressMonitor progressMonitor) {
		record ProcessorInfoCompletableFutureRecord<P>(ProcessorConfig config, CompletableFuture<ProcessorInfo<P>> processorInfoCompletableFuture) {}
		Map<Element, ProcessorInfoCompletableFutureRecord<P>> processors = Collections.synchronizedMap(new LinkedHashMap<>());
		configs.forEach(c -> processors.put(c.getElement(), new ProcessorInfoCompletableFutureRecord<P>(c, new CompletableFuture<ProcessorInfo<P>>())));
		Stream<ProcessorInfoCompletableFutureRecord<P>> recordStream = processors.values().stream();
		if (parallel) {
			recordStream = recordStream.parallel();
		}
		List<CompletionStage<?>> stages = Collections.synchronizedList(new ArrayList<>());
		BiConsumer<Element, BiConsumer<ProcessorInfo<P>,ProgressMonitor>> processorProvider = (e, bc) -> {
			ProcessorInfoCompletableFutureRecord<P> rec = processors.get(e);
			if (rec != null) {
				stages.add(rec.processorInfoCompletableFuture().thenAccept(pi -> bc.accept(pi, progressMonitor)));
			}
		};

		List<CompletionStage<?>> endpointWiringStages = Collections.synchronizedList(new ArrayList<>());		
		recordStream.forEach(r -> r.processorInfoCompletableFuture().complete(createProcessor(r.config(), parallel, processorProvider, endpointWiringStages::add, progressMonitor)));
		join(stages.stream().map(CompletionStage::toCompletableFuture).filter(cf -> !cf.isDone()).collect(Collectors.toList()));
		
		// Collecting exceptions
		List<Throwable> throwed = new ArrayList<>();
		Stream.concat(stages.stream(), endpointWiringStages.stream())
			.map(CompletionStage::toCompletableFuture)
			.filter(CompletableFuture::isCompletedExceptionally)
			.forEach(cf -> cf.handle((r,e) -> {
				if (e == null) {
					throw new IllegalArgumentException("Error shall not be null");
				}
				throwed.add(e);
				return null;
			}));
		
		onExceptionalCompletions(throwed);
		
		Map<Element, ProcessorInfo<P>> ret = new LinkedHashMap<>();
		processors.forEach((e, r) -> ret.put(e, r.processorInfoCompletableFuture().join()));
		return ret;		
	}

}
