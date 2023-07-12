package org.nasdanika.graph.processor.function;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;

public interface CompletionStageNopEndpointBiFunctionProcessorFactory<T,U,R> extends CompletionStageBiFunctionProcessorFactory<T,U,T,U,R>, NopEndpointProcessorConfigFactory<BiFunction<T,ProgressMonitor,CompletionStage<U>>, BiFunction<T,ProgressMonitor,CompletionStage<U>>, R> {
	
	@Override
	default T convertArgument(T arg) {
		return arg;
	}
	
	@Override
	default CompletionStage<U> convertResult(CompletionStage<U> result) {
		return result;
	}
	
	@Override
	default BiFunction<T,ProgressMonitor,CompletionStage<U>> createEndpoint(Connection connection, BiFunction<T,ProgressMonitor,CompletionStage<U>> handler, HandlerType type) {
		return NopEndpointProcessorConfigFactory.super.createEndpoint(connection, handler, type);
	}

}
