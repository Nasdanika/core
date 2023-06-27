package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;

public interface CompletionStageNopEndpointFunctionProcessorFactory<T,U,R> extends CompletionStageFunctionProcessorFactory<T,U,T,U,R>, NopEndpointProcessorFactory<Function<T,CompletionStage<U>>, Function<T,CompletionStage<U>>, R> {
	
	@Override
	default T convertArgument(T arg) {
		return arg;
	}
	
	@Override
	default CompletionStage<U> convertResult(CompletionStage<U> result) {
		return result;
	}
	
	@Override
	default Function<T, CompletionStage<U>> createEndpoint(Connection connection, Function<T, CompletionStage<U>> handler, HandlerType type) {
		return NopEndpointProcessorFactory.super.createEndpoint(connection, handler, type);
	}

}
