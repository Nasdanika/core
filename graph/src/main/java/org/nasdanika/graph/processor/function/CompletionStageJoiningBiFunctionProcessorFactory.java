package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

public interface CompletionStageJoiningBiFunctionProcessorFactory<T,U,V,W,R,H> extends CompletionStageBiFunctionProcessorFactory<T,U,V,W,R>, JoiningBiFunctionProcessorFactory<T,CompletionStage<U>,V,CompletionStage<W>,R,H> {
	
}
