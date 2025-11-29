package org.nasdanika.graph.processor;

import java.util.function.Function;

import reactor.core.publisher.Flux;

/**
 * Binding of {@link NopEndpointProcessorConfigFactory} to {@link Function}&lt;R, {@link Flux}&lt;P&gt;&gt;
 * This class can be used for building executable graphs where graph processors request capabilities they need (e.g. AI Chat) from
 * other graph elements by submitting requirements to endpoints and receiving reactive streams of capabilities back. 
 * 
 * An example of how it might be used - an algorithmic trading system where a trading engine requests streams of trades it may
 * execute from "trader" graph elements. These elements may, in turn, generate trades based on streams of tickers, technical 
 * indicators and other data requested from other graph elements.
 * 
 * @param <R> Capability requirement type
 * @param <C> Capability type
 */
public class CapabilityProcessorConfigFactory<R,C,K> extends NopEndpointProcessorConfigFactory<Function<R,Flux<C>>,K> {
	
	

}
