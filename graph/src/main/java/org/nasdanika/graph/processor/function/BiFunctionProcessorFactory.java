package org.nasdanika.graph.processor.function;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;

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
	
	interface NodeProcessor<T,U,V,W> extends BiFunction<T,ProgressMonitor,U> {
		
		U applyIncoming(Connection connection, T input, ProgressMonitor progressMonitor);
		
		U applyOutgoing(Connection connection, T input,	ProgressMonitor progressMonitor);
		
	}
	
	interface ConnectionProcessor<T,U,V,W> extends BiFunction<T,ProgressMonitor,U> {
		
		/**
		 * Processes invocation from connection's target incoming endpoint. 
		 * @param arg
		 * @param sourceEndpoint
		 * @param connectionProcessorConfig
		 * @return
		 */
		U targetApply(
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
				T input,
				ProgressMonitor progressMonitor,
				BiFunction<V,ProgressMonitor,W> targetEndpoint);
		
	}
	
	@Override
	default BiFunction<T,ProgressMonitor,U> createProcessor(
			ProcessorConfig<BiFunction<T,ProgressMonitor,U>, R> config, 
			boolean parallel, 
			Consumer<CompletionStage<?>> stageCollector, 
			ProgressMonitor progressMonitor) {
		
		if (config instanceof NodeProcessorConfig) {
			@SuppressWarnings("unchecked")
			NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> nodeProcessorConfig = (NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R>) config;

			Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor, W>>> incomingEndpointEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
				stageCollector.accept(incomingEndpointEntry.getValue().thenAccept(endpoint -> incomingEndpoints.put(incomingEndpointEntry.getKey(), endpoint)));
			}
						
			Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor,W>>> outgoingEndpointEntry: nodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
				stageCollector.accept(outgoingEndpointEntry.getValue().thenAccept(endpoint -> outgoingEndpoints.put(outgoingEndpointEntry.getKey(), endpoint)));				
			}
						
			NodeProcessor<T, U, V, W> nodeProcessor = createNodeProcessor(nodeProcessorConfig, incomingEndpoints, outgoingEndpoints, progressMonitor);			
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
				incomingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeProcessor.applyIncoming(incomingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> outgoingHandlerConsumerEntry: nodeProcessorConfig.getOutgoingHandlerConsumers().entrySet()) {
				outgoingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeProcessor.applyOutgoing(outgoingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			return nodeProcessor;						
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			@SuppressWarnings("unchecked")
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> connectionProcessorConfig = (ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R>) config;			
			ConnectionProcessor<T,U,V,W> connectionProcessor = createConnectionProcessor(connectionProcessorConfig, progressMonitor);
			
			stageCollector.accept(connectionProcessorConfig
				.getSourceEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setTargetHandler((t,pm) -> connectionProcessor.targetApply(t, pm, endpoint));
				}));
			
			stageCollector.accept(connectionProcessorConfig
				.getTargetEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setSourceHandler((t,pm) -> connectionProcessor.sourceApply(t, pm, endpoint));
				}));
			
			return connectionProcessor;			
		}
		
		return ProcessorFactory.super.createProcessor(config, parallel, stageCollector, progressMonitor);
	}
	
	/**
	 * Processes invocation from connection's target incoming endpoint. 
	 * @param arg
	 * @param sourceEndpoint
	 * @param connectionProcessorConfig
	 * @return
	 */
	ConnectionProcessor<T,U,V,W> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> connectionProcessorConfig,
			ProgressMonitor progressMonitor);

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
	 NodeProcessor<T,U,V,W> createNodeProcessor(
			 NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> nodeProcessorConfig,
			 Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints,
			 Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints,			 
			 ProgressMonitor progressMonitor);
	
}
