package org.nasdanika.graph.processor.function;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Creates reflective processors which pass invocations between targets.  
 */
public abstract class InvocationRequestBiFunctionProcessorFactory extends BiFunctionProcessorFactory<InvocationRequest, Object, InvocationRequest, Object> {
	
	/**
	 * First argument of target methods to differentiate between invocation sources
	 */
	public enum InvocationType {
		
		/**
		 * Invocation from the processor - apply() 
		 */
		PROCESSOR,
		
		/**
		 * Invocation from connection source - sourceApply()
		 */
		SOURCE,

		/**
		 * Invocation from connection target - targetApply()
		 */
		TARGET,
		
		/**
		 * Invocation from an outgoing connection - applyOutgoing()
		 */
		OUTGOING,
		
		/**
		 * Invocation from an incoming connection - applyIncoming()
		 */
		INCOMING	
		
	}
	
	/**
	 * Invocation targets for a connection processor
	 * @param connectionProcessorConfig
	 * @param parallel
	 * @param infoProvider
	 * @param endpointWiringStageConsumer
	 * @param progressMonitor
	 * @return
	 */
	protected abstract Collection<Object> createConnectionProcessorTargets(
			ConnectionProcessorConfig<BiFunction<InvocationRequest, ProgressMonitor, Object>, BiFunction<InvocationRequest, ProgressMonitor, Object>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<InvocationRequest, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor);

	@Override
	protected ConnectionProcessor<InvocationRequest, Object, InvocationRequest, Object> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<InvocationRequest, ProgressMonitor, Object>, BiFunction<InvocationRequest, ProgressMonitor, Object>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<InvocationRequest, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		Collection<Object> targets = createConnectionProcessorTargets(connectionProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
		return new ReflectiveBiFunctionConnectionProcessor(targets);
	}

	/**
	 * Invocation targets for a node processor
	 * @param nodeProcessorConfig
	 * @param parallel
	 * @param infoProvider
	 * @param endpointWiringStageConsumer
	 * @param incomingEndpoints
	 * @param outgoingEndpoints
	 * @param progressMonitor
	 * @return
	 */
	protected abstract Collection<Object> createNodeProcessorTargets(
			NodeProcessorConfig<BiFunction<InvocationRequest, ProgressMonitor, Object>, BiFunction<InvocationRequest, ProgressMonitor, Object>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<InvocationRequest, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<InvocationRequest, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<InvocationRequest, ProgressMonitor, Object>> outgoingEndpoints,
			ProgressMonitor progressMonitor);

	@Override
	protected NodeProcessor<InvocationRequest, Object, InvocationRequest, Object> createNodeProcessor(
			NodeProcessorConfig<BiFunction<InvocationRequest, ProgressMonitor, Object>, BiFunction<InvocationRequest, ProgressMonitor, Object>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<InvocationRequest, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<InvocationRequest, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<InvocationRequest, ProgressMonitor, Object>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		Collection<Object> targets = createNodeProcessorTargets(nodeProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, incomingEndpoints, outgoingEndpoints, progressMonitor);
		return new ReflectiveBiFunctionNodeProcessor(targets);
	}
	
}
