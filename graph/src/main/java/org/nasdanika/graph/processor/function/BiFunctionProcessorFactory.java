package org.nasdanika.graph.processor.function;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
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
public abstract class BiFunctionProcessorFactory<T,U,V,W> extends ProcessorFactory<BiFunction<T,ProgressMonitor,U>> {
	
	public interface NodeProcessor<T,U,V,W> extends BiFunction<T,ProgressMonitor,U> {
		
		U applyIncoming(Connection connection, T input, ProgressMonitor progressMonitor);
		
		U applyOutgoing(Connection connection, T input,	ProgressMonitor progressMonitor);
		
	}
	
	public interface ConnectionProcessor<T,U,V,W> extends BiFunction<T,ProgressMonitor,U> {
		
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
	protected ProcessorInfo<BiFunction<T, ProgressMonitor, U>> createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		if (config instanceof NodeProcessorConfig) {
			@SuppressWarnings("unchecked")
			NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>> nodeProcessorConfig = (NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>>) config;

			Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor, W>>> incomingEndpointEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
				endpointWiringStageConsumer.accept(incomingEndpointEntry.getValue().thenAccept(endpoint -> incomingEndpoints.put(incomingEndpointEntry.getKey(), endpoint)));
			}
						
			Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<BiFunction<V,ProgressMonitor,W>>> outgoingEndpointEntry: nodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
				endpointWiringStageConsumer.accept(outgoingEndpointEntry.getValue().thenAccept(endpoint -> outgoingEndpoints.put(outgoingEndpointEntry.getKey(), endpoint)));				
			}
						
			NodeProcessor<T, U, V, W> nodeProcessor = createNodeProcessor(nodeProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, incomingEndpoints, outgoingEndpoints, progressMonitor);			
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
				incomingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeProcessor.applyIncoming(incomingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			for (Entry<Connection, Consumer<BiFunction<T,ProgressMonitor,U>>> outgoingHandlerConsumerEntry: nodeProcessorConfig.getOutgoingHandlerConsumers().entrySet()) {
				outgoingHandlerConsumerEntry.getValue().accept((t,pm) -> nodeProcessor.applyOutgoing(outgoingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			return ProcessorInfo.of(nodeProcessorConfig, nodeProcessor);						
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			@SuppressWarnings("unchecked")
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>> connectionProcessorConfig = (ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>>) config;			
			ConnectionProcessor<T,U,V,W> connectionProcessor = createConnectionProcessor(connectionProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
			
			endpointWiringStageConsumer.accept(connectionProcessorConfig
				.getSourceEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setTargetHandler((t,pm) -> connectionProcessor.targetApply(t, pm, endpoint));
				}));
			
			endpointWiringStageConsumer.accept(connectionProcessorConfig
				.getTargetEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setSourceHandler((t,pm) -> connectionProcessor.sourceApply(t, pm, endpoint));
				}));
			
			return ProcessorInfo.of(connectionProcessorConfig, connectionProcessor);			
		}
		
		return super.createProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
	}
	
	/**
	 * Processes invocation from connection's target incoming endpoint. 
	 * @param arg
	 * @param sourceEndpoint
	 * @param connectionProcessorConfig
	 * @return
	 */
	protected abstract ConnectionProcessor<T,U,V,W> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			
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
	protected abstract NodeProcessor<T,U,V,W> createNodeProcessor(
			NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			 
			Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints,
			Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints,			 
			ProgressMonitor progressMonitor);
	
}
