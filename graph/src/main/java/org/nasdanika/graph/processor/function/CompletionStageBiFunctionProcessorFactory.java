package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

public interface CompletionStageBiFunctionProcessorFactory<T,U,V,W,R> extends BiFunctionProcessorFactory<T,CompletionStage<U>,V,CompletionStage<W>,R> {
	
}
