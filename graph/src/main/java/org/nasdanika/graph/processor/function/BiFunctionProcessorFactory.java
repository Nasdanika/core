package org.nasdanika.graph.processor.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
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
 * A processor factory with {@link BiFunction} handler and endpoint
 * @param <P> Processor type
 * @param <T> Handler parameter type
 * @param <U> Handler return type
 * @param <V> Endpoint parameter type
 * @param <W> Endpoint return type
 * @param <R> Registry type
 */
public interface BiFunctionProcessorFactory<T,U,V,W,R> extends ProcessorFactory<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> {
	
	@Override
	default boolean isPassThrough(Connection connection) {
		return false;
	}
	
	@Override
	default BiFunction<V,ProgressMonitor, W> createEndpoint(Connection connection, BiFunction<T,ProgressMonitor, U> handler, HandlerType type) {
		return (arg, progressMonitor) -> convertResult(handler.apply(convertArgument(arg), adaptProgressMonitor(progressMonitor)));
	}
	
	/**
	 * Allows to modify the progress monitor argument.
	 * @param progressMonitor
	 * @return
	 */
	default ProgressMonitor adaptProgressMonitor(ProgressMonitor progressMonitor) {
		return progressMonitor;
	}
	
	T convertArgument(V arg);
	
	W convertResult(U result);
	
	@Override
	default ProcessorInfo<BiFunction<T,ProgressMonitor,U>, R> createProcessor(ProcessorConfig<BiFunction<T,ProgressMonitor,U>, R> config, ProgressMonitor progressMonitor) {
		if (config instanceof NodeProcessorConfig) {
			@SuppressWarnings("unchecked")
			NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> nodeProcessorConfig = (NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>,R>) config;			

			Collection<Throwable> failures = new ArrayList<>();
			Function<Throwable, Void> failureHandler  = failure -> {
				if (failure != null) {
					failures.add(failure);
				}
				return null;
			};

			Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor, W>>> incomingEndpointEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
				incomingEndpointEntry.getValue().thenAccept(endpoint -> incomingEndpoints.put(incomingEndpointEntry.getKey(), endpoint)).exceptionally(failureHandler);
			}
						
			Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor,W>>> outgoingEndpointEntry: nodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
				outgoingEndpointEntry.getValue().thenAccept(endpoint -> outgoingEndpoints.put(outgoingEndpointEntry.getKey(), endpoint)).exceptionally(failureHandler);				
			}		
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
				incomingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeApply(nodeProcessorConfig, incomingHandlerConsumerEntry.getKey(), true, t, pm, incomingEndpoints, outgoingEndpoints, failures));
			}
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> outgoingHandlerConsumerEntry: nodeProcessorConfig.getOutgoingHandlerConsumers().entrySet()) {
				outgoingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeApply(nodeProcessorConfig, outgoingHandlerConsumerEntry.getKey(), false, t, pm, incomingEndpoints, outgoingEndpoints, failures));				
			}
			
			return ProcessorInfo.of(
					config,
					(t,pm) -> nodeApply(nodeProcessorConfig, null, false, t, pm, incomingEndpoints, outgoingEndpoints, failures),
					() -> failures);						
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			@SuppressWarnings("unchecked")
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> connectionProcessorConfig = (ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R>) config;
			
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
					connectionProcessorConfig.setTargetHandler((t,pm) -> targetApply(connectionProcessorConfig, t, pm, endpoint));
				})
				.exceptionally(failureHandler);
			
			connectionProcessorConfig
				.getTargetEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setSourceHandler((t,pm) -> sourceApply(connectionProcessorConfig, t, pm, endpoint));
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
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> connectionProcessorConfig,
			T input,
			ProgressMonitor progressMonitor,
			BiFunction<V,ProgressMonitor,W> sourceEndpoint);
	
	/**
	 * Processes invocation from connection's source outgoing endpoint.
	 * @param arg
	 * @param sourceEndpoint
	 * @param connectionProcessorConfig
	 * @return
	 */
	U sourceApply(
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> connectionProcessorConfig,
			T input,
			ProgressMonitor progressMonitor,
			BiFunction<V,ProgressMonitor,W> targetEndpoint);

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
			NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> nodeProcessorConfig,
			Connection connection,
			boolean isIncoming,
			T input,
			ProgressMonitor progressMonitor,
			Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints,
			Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints,			
			Collection<Throwable> failures);
	
	
}
