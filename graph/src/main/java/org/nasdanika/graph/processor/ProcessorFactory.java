package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.Composeable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
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
public interface ProcessorFactory<P,H,E,R> extends Composeable<ProcessorFactory<P,H,E,R>> {
	
	public abstract class Helper<P,R> {
		
		private ProcessorConfig<R> config;

		Helper(ProcessorConfig<R> config) {
			this.config = config;
		}
		
		abstract void setParentProcessorInfo(ProcessorInfo<P,R> parentProcessorInfo);
		
		@Override
		public ProcessorConfig<P, R> getConfig() {
			return config;
		}
		
		abstract void setRegistry(R registry);	
			
		public abstract CompletionStage<P> getProcessorCompletionStage();
		
		abstract void createProcessor(Function<ProcessorConfig<R>,  P> constructor);	
		
	}	
	
	/**
	 * If a connection is pass-through its source endpoint is connected directly to the target node handler and vice versa.
	 * @param connection
	 * @return
	 */
	default boolean isPassThrough(Connection connection) {
		return true;
	};
	
	/**
	 * Creates an endpoint to invoke the argument handler of specified type.
	 * @param connection
	 * @param handler
	 * @param type
	 * @return
	 */
	E createEndpoint(Connection connection, H handler, HandlerType type);
	
	default R createProcessors(ProgressMonitor progressMonitor, BiFunction<ProcessorConfig<P,R>, ProgressMonitor, P> constructor, boolean parallel, Element... elements) {
		Stream<Element> stream = Arrays.stream(elements);
		return createProcessors(parallel ? stream.parallel() : stream , constructor, parallel, progressMonitor);
	}
	
	default R createProcessors(Collection<? extends Element> elements, Constructor<P,R> constructor, boolean parallel, ProgressMonitor progressMonitor) {
		return createProcessors(parallel ? elements.parallelStream() : elements.stream(), constructor, parallel, progressMonitor);
	}
	
	default R createProcessors(Stream<? extends Element> elements, Constructor<P,R> constructor, boolean parallel, ProgressMonitor progressMonitor) {
		ProcessorFactoryVisitor<P, H, E, R> visitor = new ProcessorFactoryVisitor<>(this);				
		BiFunction<Element, Map<? extends Element, Helper<P,R>>, Helper<P,R>> createElementProcessor = (element, childProcessors) -> visitor.createElementProcessorHelper(element, childProcessors, progressMonitor);
		List<Helper<P,R>> helpers = elements.map(element -> element.accept(createElementProcessor)).collect(Collectors.toList());
		R registry = createRegistry(visitor.getRegistry());
		Stream<Helper<P,R>> helpersStream = parallel ? helpers.parallelStream() : helpers.stream();
		List<CompletionStage<?>> toComplete = Collections.synchronizedList(new ArrayList<>());
		BiFunction<ProcessorConfig<P,R>, Consumer<CompletionStage<?>>, P> processorConstructor = (config, stageCollector) -> constructor.createProcessor(config, stageCollector, progressMonitor);
		helpersStream.forEach(helper -> {
			helper .setProcessor(createProcessor(helper.getConfig(), parallel, toComplete::add, progressMonitor));
		});
		
		helpers.forEach(helper -> helper.setRegistry(registry));
		
		// Collecting exceptions
		CompletableFuture<?>[] toCompleteArray = toComplete.stream().map(CompletionStage::toCompletableFuture).filter(CompletableFuture::isCompletedExceptionally).collect(Collectors.toList()).toArray(new CompletableFuture[0]);
		
		CompletableFuture.allOf(toCompleteArray).handle((r, e) -> {
			if (e == null) {
				return registry;
			}
			NasdanikaException ne = new NasdanikaException("Theres's been errors during processor creation");
			for (CompletableFuture<?> cf: toCompleteArray) {
				if (cf.isCompletedExceptionally()) {
					cf.whenComplete((rs, ex) -> ne.addSuppressed(ex));
				}
			}
			throw ne;
		}).join();
		
		return registry;		
	}
	
	/**
	 * Creates a registry from a map of elements to processor infos. Implementations of this method may just return the argument registry or wrap it.
	 * @param registry
	 * @return
	 */
	R createRegistry(Map<Element, ProcessorInfo<P,R>> registry);
	
	/**
	 * Composes this and the other factory
	 */
	@Override
	default ProcessorFactory<P, H, E, R> compose(ProcessorFactory<P, H, E, R> other) {
		if (other == null) {
			return this;
		}
		
		return new ProcessorFactory<P, H, E, R>() {

			@Override
			public E createEndpoint(Connection connection, H handler, HandlerType type) {
				E endpoint = ProcessorFactory.this.createEndpoint(connection, handler, type);
				if (endpoint != null) {
					return endpoint;
				}
				return other.createEndpoint(connection, handler, type);
			}
			
			@Override
			public P createProcessor(ProcessorConfig<P, R> config, boolean parallel, Consumer<CompletionStage<?>> stageCollector, ProgressMonitor progressMonitor) {				
				P processor = ProcessorFactory.this.createProcessor(config, parallel, stageCollector, progressMonitor);
				return processor == null ? other.createProcessor(config, parallel, stageCollector, progressMonitor) : processor;
			}

			@Override
			public R createRegistry(Map<Element, ProcessorInfo<P, R>> registry) {
				return ProcessorFactory.this.createRegistry(registry);
			}
		};
		
	}
	
	/**
	 * Composes a stream of factories into a single factory.
	 * @param <P>
	 * @param <H>
	 * @param <E>
	 * @param factories
	 * @return
	 */
	static <P, H, E, R> Optional<ProcessorFactory<P, H, E, R>> compose(Stream<ProcessorFactory<P, H, E, R>> factories) {
		return factories.reduce(Composeable::composer);
	}

}
