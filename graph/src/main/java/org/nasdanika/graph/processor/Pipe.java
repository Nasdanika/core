package org.nasdanika.graph.processor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * Two connected synapses
 * @param <H>
 * @param <E>
 */
public interface Pipe<H,E> {
	
	Synapse<H,E> getSource();
	
	Synapse<H,E> getTarget();	
	
	static <H,E> Pipe<H,E> create(Function<H,E> sourceEndpointFactory, Function<H,E> targetEndpointFactory) {
		CompletableFuture<E> sourceEndpoint = new CompletableFuture<>();
		CompletableFuture<E> targetEndpoint = new CompletableFuture<>();
				
		Synapse<H, E> sourceSynapse = new Synapse<H,E>() {			
			

			@Override
			public CompletionStage<E> getEndpoint() {
				return sourceEndpoint;
			}

			@Override
			public boolean setHandler(H handler) {
				return targetEndpoint.complete(targetEndpointFactory.apply(handler));
			}
		};
		
		Synapse<H, E> targetSynapse = new Synapse<H,E>() {			
			

			@Override
			public CompletionStage<E> getEndpoint() {
				return targetEndpoint;
			}

			@Override
			public boolean setHandler(H handler) {
				return sourceEndpoint.complete(sourceEndpointFactory.apply(handler));
			}
		};
		
		return new Pipe<H, E>() {

			@Override
			public Synapse<H, E> getSource() {
				return sourceSynapse;
			}

			@Override
			public Synapse<H, E> getTarget() {
				return targetSynapse;
			}
			
		};
		
	}
		
	static <H> Pipe<H,H> create() {
		return create(Function.identity(), Function.identity());
	}
	
}
