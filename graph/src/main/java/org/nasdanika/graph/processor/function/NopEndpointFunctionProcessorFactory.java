package org.nasdanika.graph.processor.function;

import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;

public interface NopEndpointFunctionProcessorFactory<T,U,R> extends FunctionProcessorFactory<T,U,T,U,R>, NopEndpointProcessorFactory<Function<T,U>, Function<T,U>, R> {
	
	@Override
	default T convertArgument(T arg) {
		return arg;
	}
	
	@Override
	default U convertResult(U result) {
		return result;
	}
	
	@Override
	default Function<T, U> createEndpoint(Connection connection, Function<T, U> handler, HandlerType type) {
		return NopEndpointProcessorFactory.super.createEndpoint(connection, handler, type);
	}

}
