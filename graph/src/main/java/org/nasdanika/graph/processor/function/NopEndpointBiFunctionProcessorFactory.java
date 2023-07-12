package org.nasdanika.graph.processor.function;

import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;

public interface NopEndpointBiFunctionProcessorFactory<T,U,R> extends BiFunctionProcessorFactory<T,U,T,U,R>, NopEndpointProcessorConfigFactory<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, R> {
	
	@Override
	default T convertArgument(T arg) {
		return arg;
	}
	
	@Override
	default U convertResult(U result) {
		return result;
	}
	
	@Override
	default BiFunction<T,ProgressMonitor,U> createEndpoint(Connection connection, BiFunction<T,ProgressMonitor,U> handler, HandlerType type) {
		return NopEndpointProcessorConfigFactory.super.createEndpoint(connection, handler, type);
	}

}
