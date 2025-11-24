package org.nasdanika.graph.processor;

import java.util.function.Function;

import reactor.core.publisher.Flux;

/**
 * Binding of {@link ReflectiveProcessorFactoryProvider} to {@link Function}&lt;R, {@link Flux}&lt;P&gt;&gt;
 * @param <R> Capability requirement type
 * @param <C> Capability type
 * @param <P> Processor type
 */
public class CapabilityReflectiveProcessorFactoryProvider<R,C,P> extends ReflectiveProcessorFactoryProvider<Function<R,Flux<C>>,Function<R,Flux<C>>,P> {

	protected CapabilityReflectiveProcessorFactoryProvider(Object... targets) {
		super(targets);
	}		

}
