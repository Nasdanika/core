package org.nasdanika.graph.processor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

abstract class Helper<P,R> implements ProcessorInfo<P,R> {
	
	private ProcessorConfig<P,R> config;
	private CompletableFuture<P> processorCompletableFuture = new CompletableFuture<>();

	Helper(ProcessorConfig<P,R> config) {
		this.config = config;
	}
	
	abstract void setParentProcessorInfo(ProcessorInfo<P,R> parentProcessorInfo);
	
	@Override
	public ProcessorConfig<P, R> getConfig() {
		return config;
	}
	
	void setProcessor(P processor, Throwable ex) {
		if (ex == null) {
			processorCompletableFuture.complete(processor);
		} else {
			processorCompletableFuture.completeExceptionally(ex);
		}
	}
	
	@Override
	public CompletionStage<P> getProcessor() {
		return processorCompletableFuture;
	}
	
	abstract void setRegistry(R registry);	
	
}
