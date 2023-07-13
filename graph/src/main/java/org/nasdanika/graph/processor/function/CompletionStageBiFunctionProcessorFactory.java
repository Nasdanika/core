package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

/**
 * Binding to completion stage
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <W>
 */
public abstract class CompletionStageBiFunctionProcessorFactory<T,U,V,W> extends BiFunctionProcessorFactory<T,CompletionStage<U>,V,CompletionStage<W>> {
	
}
