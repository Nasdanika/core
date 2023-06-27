package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

public interface CompletionStageJoiningFunctionProcessorFactory<T,U,V,W,R> extends CompletionStageFunctionProcessorFactory<T,U,V,W,R>, JoiningFunctionProcessorFactory<T,CompletionStage<U>,V,CompletionStage<W>,R> {
	
}
