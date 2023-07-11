package org.nasdanika.graph.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.Composeable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Processor config factory creates and "wires" {@link ProcessorConfig}s so they can be used to create processors.
 * @author Pavel
 *
 */
public interface ProcessorConfigFactory<H,E> {
	
	
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
	
	default Map<Element, ProcessorConfig> createConfigs(ProgressMonitor progressMonitor, boolean parallel, Element... elements) {
		Stream<Element> stream = Arrays.stream(elements);
		return createProcessors(parallel ? stream.parallel() : stream , parallel, progressMonitor);
	}
	
	default Map<Element, ProcessorConfig> createProcessors(Collection<? extends Element> elements, boolean parallel, ProgressMonitor progressMonitor) {
		return createProcessors(parallel ? elements.parallelStream() : elements.stream(), parallel, progressMonitor);
	}
	
	default Map<Element, ProcessorConfig> createProcessors(Stream<? extends Element> elements, boolean parallel, ProgressMonitor progressMonitor) {
		ProcessorConfigFactoryVisitor<H, E> visitor = new ProcessorConfigFactoryVisitor<>(this);				
		BiFunction<Element, Map<? extends Element, ProcessorConfigFactoryVisitor.Registration>, ProcessorConfigFactoryVisitor.Registration> createElementProcessorConfig = (element, childProcessors) -> visitor.createElementProcessorHelper(element, childProcessors, progressMonitor);
		Map<Element, ProcessorConfig> registry = new LinkedHashMap<>();
		visitor.getRegistry().forEach((e,r) -> registry.put(e, r.getConfig()));		
		Stream<ProcessorConfigFactoryVisitor.Registration> registrations = elements.map(element -> element.accept(createElementProcessorConfig)).collect(Collectors.toList()).stream();
		if (parallel) {
			registrations = registrations.parallel();
		}
		registrations.forEach(helper -> helper.setRegistry(registry));
		return registry;		
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
