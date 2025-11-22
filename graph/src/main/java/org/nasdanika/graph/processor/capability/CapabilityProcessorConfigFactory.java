package org.nasdanika.graph.processor.capability;

import java.util.function.Function;

import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;

import reactor.core.publisher.Flux;

/**
 * Binding of {@link NopEndpointProcessorConfigFactory} to {@link Function}&lt;R, {@link Flux}&lt;P&gt;&gt;
 * @param <R> Capability requirement type
 * @param <C> Capability type
 */
public class CapabilityProcessorConfigFactory<R,C> extends NopEndpointProcessorConfigFactory<Function<R,Flux<C>>> {
	
	

}
