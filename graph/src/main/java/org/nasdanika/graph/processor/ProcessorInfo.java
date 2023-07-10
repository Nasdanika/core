package org.nasdanika.graph.processor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface ProcessorInfo<P,R> {
	
	ProcessorConfig<P,R> getConfig();
	
	CompletionStage<P> getProcessor();
	
	
	static <P,R> ProcessorInfo<P,R> of(ProcessorConfig<P,R> config, P processor) {
		
		CompletionStage<P> pcs = CompletableFuture.completedStage(processor);
		
		return new ProcessorInfo<P,R>() {

			@Override
			public ProcessorConfig<P,R> getConfig() {
				return config;
			}

			@Override
			public CompletionStage<P> getProcessor() {
				return pcs;
			}
			
		};
	}

}
