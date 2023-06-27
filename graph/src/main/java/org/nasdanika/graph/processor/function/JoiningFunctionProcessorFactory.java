package org.nasdanika.graph.processor.function;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * A processor factory which stores and joins results of enpdoint and client code invocations.
 * It can be used, for example, for computation of node positions in a force layout graph, or to build a graph neural network. 
 */

public interface JoiningFunctionProcessorFactory<T,U,V,W,R> extends FunctionProcessorFactory<T,U,V,W,R> {
	
	@Override
	default U nodeApply(Connection connection, boolean isIncoming, T arg,
			Map<Connection, Function<V, W>> incomingEndpoints, Map<Connection, Function<V, W>> outgoingEndpoints,
			NodeProcessorConfig<Function<T, U>, Function<T, U>, Function<V, W>, R> nodeProcessorConfig,
			Collection<Throwable> failures) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default U sourceApply(T arg, Function<V, W> targetEndpoint,
			ConnectionProcessorConfig<Function<T, U>, Function<T, U>, Function<V, W>, R> connectionProcessorConfig) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default U targetApply(T arg, Function<V, W> sourceEndpoint,
			ConnectionProcessorConfig<Function<T, U>, Function<T, U>, Function<V, W>, R> connectionProcessorConfig) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO
	

}
