package org.nasdanika.graph.model.util;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactoryProvider.ConnectionProcessorFactory;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactoryProvider.NodeProcessorFactory;

import reactor.core.publisher.Flux;

/**
 * Creates processors for flow elements
 */
public class ReflectiveProcessorFactory {
	
	public record ConnectionProcessorRequirement(
			Object requirement,
			ConnectionProcessorConfig<BiFunction<Object,ProgressMonitor,Object>, BiFunction<Object,ProgressMonitor,Object>> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Context context) {}
	
	public static ConnectionProcessorRequirement createConnectionProcessorRequirement(
			Object requirement,
			ConnectionProcessorConfig<BiFunction<Object,ProgressMonitor,Object>, BiFunction<Object,ProgressMonitor,Object>> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Context context) {
		return new ConnectionProcessorRequirement(requirement, config, infoProvider, endpointWiringStageConsumer, context);
	}
	
	public record NodeProcessorRequirement(
			Object requirement,
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config, 
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			Context context) {}

	public static NodeProcessorRequirement createNodeProcessorRequirement(
			Object requirement,
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config, 
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			Context context) {
		return new NodeProcessorRequirement(requirement, config, infoProvider, endpointWiringStageConsumer, incomingEndpoints, outgoingEndpoints, context);  
	}
	
	private CapabilityLoader capabilityLoader = new CapabilityLoader();
	
	private Context context;
	private Object requirement;

	public ReflectiveProcessorFactory(Object requirement, Context context) {
		this.requirement = requirement;
		this.context = context;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Class<ConnectionProcessor<Object, Object, Object, Object>> CONNECTION_PROCESSOR_CLASS = (Class) ConnectionProcessor.class;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Class<NodeProcessor<Object, Object, Object, Object>> NODE_PROCESSOR_CLASS = (Class) NodeProcessor.class;
	
	@ConnectionProcessorFactory
	public ConnectionProcessor<Object, Object, Object, Object> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<Object,ProgressMonitor,Object>, BiFunction<Object,ProgressMonitor,Object>> config,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			
			ProgressMonitor progressMonitor) {
		
		ConnectionProcessorRequirement requirement = createConnectionProcessorRequirement(this.requirement, config, infoProvider, endpointWiringStageConsumer, context);
		for (CapabilityProvider<ConnectionProcessor<Object, Object, Object, Object>> cp: capabilityLoader.loadServices(CONNECTION_PROCESSOR_CLASS, null,  requirement, progressMonitor)) {
			Flux<ConnectionProcessor<Object, Object, Object, Object>> publisher = cp.getPublisher();			
			Optional<ConnectionProcessor<Object, Object, Object, Object>> sOpt = publisher.toStream().findAny();
			if (sOpt.isPresent()) {
				return sOpt.get();
			}
		}
		return null;
	}

	@NodeProcessorFactory
	public NodeProcessor<Object, Object, Object, Object> createNodeProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		
		NodeProcessorRequirement requirement = createNodeProcessorRequirement(
				this.requirement,
				config, 
				infoProvider, 
				endpointWiringStageConsumer,
				incomingEndpoints,
				outgoingEndpoints,
				context);
		
		for (CapabilityProvider<NodeProcessor<Object, Object, Object, Object>> cp: capabilityLoader.loadServices(NODE_PROCESSOR_CLASS, null,  requirement, progressMonitor)) {
			Flux<NodeProcessor<Object, Object, Object, Object>> publisher = cp.getPublisher();			
			Optional<NodeProcessor<Object, Object, Object, Object>> sOpt = publisher.toStream().findAny();
			if (sOpt.isPresent()) {
				return sOpt.get();
			}
		}
		return null;
	}	

}
