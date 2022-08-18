package org.nasdanika.graph.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Processor factory creates and "wires" processors.
 * @author Pavel
 *
 * @param <P> Processor type - what is passed to parent/child processors and also returned to the client code to interact with the processor network. For model elements which are not interacted with by other processors or the client code the result may be null.
 * @param <H> Handler type. Handlers are invoked by endpoints. 
 * @param <E> Endpoint type. Endpoints pass invocations to handlers.
 */
public interface ProcessorFactory<P,H,E> {
	
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
	
	default ProcessorInfo<P> createProcessor(
			ProcessorConfig<P> config, 
			Consumer<Consumer<ProcessorInfo<P>>> setParentProcessorInfoCallback,
			Consumer<Consumer<Map<Element, ProcessorInfo<P>>>> setRegistryCallback) {
		return new ProcessorInfo<P>() {

			@Override
			public ProcessorConfig<P> getConfig() {
				return config;
			}

			@Override
			public P getProcessor() {
				return null;
			}
		};
	}
	
	default Map<Element,ProcessorInfo<P>> createProcessors(Element... elements) {
		return createProcessors(Arrays.stream(elements));
	}
	
	default Map<Element,ProcessorInfo<P>> createProcessors(Collection<Element> elements) {
		return createProcessors(elements.stream());
	}
	
	default Map<Element,ProcessorInfo<P>> createProcessors(Stream<Element> elements) {
		ProcessorFactoryVisitor<P, H, E> visitor = new ProcessorFactoryVisitor<>(this);				
		List<Helper<P>> helpers = elements.map(element -> element.accept(visitor::createElementProcessor)).collect(Collectors.toList());
		Map<Element, ProcessorInfo<P>> registry = visitor.getRegistry();
		helpers.forEach(helper -> helper.setRegistry(registry));
		return Collections.unmodifiableMap(registry);		
	}
	
	/**
	 * Handler proxy passes invocations to the handler returned by the supplier. 
	 * This method is used to create endpoints before actual handlers are provided by processors.
	 * @param connection
	 * @param handlerSupplier
	 * @param type
	 * @return
	 */
	H createHandlerProxy(Connection connection, Supplier<H> handlerSupplier, HandlerType type);

}
