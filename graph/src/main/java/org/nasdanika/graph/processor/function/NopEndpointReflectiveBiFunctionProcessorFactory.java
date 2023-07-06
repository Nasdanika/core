package org.nasdanika.graph.processor.function;

public abstract class NopEndpointReflectiveBiFunctionProcessorFactory<T, U, R> extends ReflectiveBiFunctionProcessorFactory<T, U, T, U, R> implements NopEndpointBiFunctionProcessorFactory<T, U, R> {

	public NopEndpointReflectiveBiFunctionProcessorFactory(Object... targets) {
		super(targets);
	}
	
}
