package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
	
	default ElementProcessorInfo<P> createProcessor(
			ElementProcessorConfig<P> config, 
			Consumer<Consumer<ElementProcessorInfo<P>>> setParentProcessorInfoCallback,
			Consumer<Consumer<Map<Element, ElementProcessorInfo<P>>>> setRegistryCallback) {
		return new ElementProcessorInfo<P>() {

			@Override
			public ElementProcessorConfig<P> getConfig() {
				return config;
			}

			@Override
			public P getProcessor() {
				return null;
			}
		};
	}
	
	default Map<Element,ElementProcessorInfo<P>> createProcessors(Element element) {
		ProcessorFactoryVisitor<P, H, E> visitor = new ProcessorFactoryVisitor<>(this);				
		Helper<P> helper = element.accept(visitor::createElementProcessor);
		helper.setRegistry(visitor.getRegistry());
		return Collections.unmodifiableMap(visitor.getRegistry());		
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
