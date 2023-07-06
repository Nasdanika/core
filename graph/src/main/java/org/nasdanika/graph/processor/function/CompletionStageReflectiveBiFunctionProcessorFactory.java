package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;

public abstract class CompletionStageReflectiveBiFunctionProcessorFactory<T, U, V, W, R>	extends ReflectiveBiFunctionProcessorFactory<T, CompletionStage<U>, V, CompletionStage<W>, R> implements CompletionStageBiFunctionProcessorFactory<T, U, V, W, R> {
	
	protected CompletionStageReflectiveBiFunctionProcessorFactory(Object... targets) {
		super(targets);
	}

}
