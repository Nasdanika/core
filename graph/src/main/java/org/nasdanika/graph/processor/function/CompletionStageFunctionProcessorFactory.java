package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

public interface CompletionStageFunctionProcessorFactory<T,U,V,W,R> extends FunctionProcessorFactory<T,CompletionStage<U>,V,CompletionStage<W>,R> {
	
}
