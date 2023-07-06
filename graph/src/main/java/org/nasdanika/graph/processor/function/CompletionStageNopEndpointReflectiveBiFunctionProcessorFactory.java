package org.nasdanika.graph.processor.function;

public abstract class CompletionStageNopEndpointReflectiveBiFunctionProcessorFactory<T, U, R> extends CompletionStageReflectiveBiFunctionProcessorFactory<T, U, T, U, R>	implements CompletionStageNopEndpointBiFunctionProcessorFactory<T, U, R> {

	protected CompletionStageNopEndpointReflectiveBiFunctionProcessorFactory(Object... targets) {
		super(targets);
	}

}
