package org.nasdanika.graph.processor.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * A processor factory with {@link Function} handler and endpoint
 * @param <P> Processor type
 * @param <T> Handler parameter type
 * @param <U> Handler return type
 * @param <V> Endpoint parameter type
 * @param <W> Endpoint return type
 * @param <R> Registry type
 */
public interface FunctionProcessorFactory<T,U,V,W,R> extends ProcessorFactory<Function<T,U>, Function<T,U>, Function<V,W>, R> {
	
	@Override
	default Function<V, W> createEndpoint(Connection connection, Function<T, U> handler, HandlerType type) {
		return arg -> convertResult(handler.apply(convertArgument(arg)));
	}
	
	T convertArgument(V arg);
	
	W convertResult(U result);
	
	@Override
	default ProcessorInfo<Function<T,U>, R> createProcessor(ProcessorConfig<Function<T,U>, R> config, ProgressMonitor progressMonitor) {
		if (config instanceof NodeProcessorConfig) {
			@SuppressWarnings("unchecked")
			NodeProcessorConfig<Function<T,U>, Function<T,U>, Function<V,W>, R> nodeProcessorConfig = (NodeProcessorConfig<Function<T,U>, Function<T, U>, Function<V, W>, R>) config;			

			Collection<Throwable> failures = new ArrayList<>();
			Function<Throwable, Void> failureHandler  = failure -> {
				if (failure != null) {
					failures.add(failure);
				}
				return null;
			};

			Map<Connection, Function<V, W>> incomingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<Function<V, W>>> incomingEndpointEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
				incomingEndpointEntry.getValue().thenAccept(endpoint -> incomingEndpoints.put(incomingEndpointEntry.getKey(), endpoint)).exceptionally(failureHandler);
			}
						
			Map<Connection, Function<V, W>> outgoingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<Function<V, W>>> outgoingEndpointEntry: nodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
				outgoingEndpointEntry.getValue().thenAccept(endpoint -> outgoingEndpoints.put(outgoingEndpointEntry.getKey(), endpoint)).exceptionally(failureHandler);				
			}		
			
			for (Entry<Connection, Consumer<Function<T, U>>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
				incomingHandlerConsumerEntry.getValue().accept(t -> nodeApply(incomingHandlerConsumerEntry.getKey(), true, t, incomingEndpoints, outgoingEndpoints, nodeProcessorConfig, failures));
			}
			
			for (Entry<Connection, Consumer<Function<T, U>>> outgoingHandlerConsumerEntry: nodeProcessorConfig.getOutgoingHandlerConsumers().entrySet()) {
				outgoingHandlerConsumerEntry.getValue().accept(t -> nodeApply(outgoingHandlerConsumerEntry.getKey(), false, t, incomingEndpoints, outgoingEndpoints, nodeProcessorConfig, failures));				
			}
			
			return ProcessorInfo.of(
					config,
					t -> nodeApply(null, false, t, incomingEndpoints, outgoingEndpoints, nodeProcessorConfig, failures),
					() -> failures);						
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			@SuppressWarnings("unchecked")
			ConnectionProcessorConfig<Function<T,U>, Function<T,U>, Function<V,W>, R> connectionProcessorConfig = (ConnectionProcessorConfig<Function<T,U>, Function<T, U>, Function<V, W>, R>) config;
			
			Collection<Throwable> failures = new ArrayList<>();
			Function<Throwable, Void> failureHandler  = failure -> {
				if (failure != null) {
					failures.add(failure);
				}
				return null;
			};
			
			connectionProcessorConfig
				.getSourceEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setTargetHandler(t -> targetApply(t, endpoint, connectionProcessorConfig));
				})
				.exceptionally(failureHandler);
			
			connectionProcessorConfig
				.getTargetEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setSourceHandler(t -> sourceApply(t, endpoint, connectionProcessorConfig));
				})
				.exceptionally(failureHandler);
			
			return ProcessorInfo.of(config, null, () -> failures);			
		}
		
		return ProcessorFactory.super.createProcessor(config, progressMonitor);
	}
	
	/**
	 * Processes invocation from connection's target incoming endpoint. 
	 * @param arg
	 * @param sourceEndpoint
	 * @param connectionProcessorConfig
	 * @return
	 */
	U targetApply(
			T arg,
			Function<V, W> sourceEndpoint,
			ConnectionProcessorConfig<Function<T,U>, Function<T,U>, Function<V,W>, R> connectionProcessorConfig);
	
	/**
	 * Processes invocation from connection's source outgoing endpoint.
	 * @param arg
	 * @param sourceEndpoint
	 * @param connectionProcessorConfig
	 * @return
	 */
	U sourceApply(
			T arg,
			Function<V, W> targetEndpoint,
			ConnectionProcessorConfig<Function<T,U>, Function<T,U>, Function<V,W>, R> connectionProcessorConfig);

	/**
	 * Processes invocation from a connection endpoint or the client via the processor.
	 * @param connection Activating connection, null if activated by the client code via the processor.
	 * @param isIncoming true if activated by the incoming connection. 
	 * @param arg Argument
	 * @param incomingEndpoints Incoming endpoints map. Maybe incomplete if not all endpoints yet has been created
	 * @param outgoingEndpoints Outgoing endpoints map. Maybe incomplete if not all endpoints yet has been created
	 * @param nodeProcessorConfig Node processor config
	 * @param failures Endpoint wiring failures
	 * @return
	 */
	U nodeApply(
			Connection connection,
			boolean isIncoming,
			T arg,
			Map<Connection, Function<V,W>> incomingEndpoints,
			Map<Connection, Function<V,W>> outgoingEndpoints,			
			NodeProcessorConfig<Function<T,U>, Function<T,U>, Function<V,W>, R> nodeProcessorConfig,
			Collection<Throwable> failures);
	
	
}
