package org.nasdanika.graph.model.util;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.model.adapters.ConnectionAdapter;
import org.nasdanika.graph.model.adapters.NodeAdapter;
//import org.nasdanika.common.Reflector.Factory;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactoryProvider.ConnectionProcessorFactory;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactoryProvider.NodeProcessorFactory;

/**
 * Creates processors for flow elements
 */
public class ReflectiveProcessorFactory {
	
	private Context context;

	public ReflectiveProcessorFactory(Context context) {
		this.context = context;
	}
	
	@ConnectionProcessorFactory
	public ConnectionProcessor<Object, Object, Object, Object> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<Object,ProgressMonitor,Object>, BiFunction<Object,ProgressMonitor,Object>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			
			ProgressMonitor progressMonitor) {
		
		org.nasdanika.graph.model.Connection<?> connection = (org.nasdanika.graph.model.Connection<?>) ((ConnectionAdapter) connectionProcessorConfig.getElement()).get();
		
		Iterable<org.nasdanika.graph.model.util.ConnectionProcessorFactory> connectionFactories = ServiceLoader.load(org.nasdanika.graph.model.util.ConnectionProcessorFactory.class);
		org.nasdanika.graph.model.util.ConnectionProcessorFactory factory = null;
		for (org.nasdanika.graph.model.util.ConnectionProcessorFactory candidate: connectionFactories) {
			if (candidate.canHandle(connection)) {
				if (factory == null || factory.priority() > candidate.priority()) {
					factory = candidate;
				}
			}
		}
		
		if (factory == null) {
			throw new UnsupportedOperationException("Factory not found for connection: " + connection); 
		}
		
		return factory.createProcessor(
				connectionProcessorConfig, 
				infoProvider, 
				endpointWiringStageConsumer, 
				context, 
				progressMonitor);
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
		
		
		EObject node = ((NodeAdapter) config.getElement()).get();
		
		Iterable<org.nasdanika.graph.model.util.NodeProcessorFactory> nodeFactories = ServiceLoader.load(org.nasdanika.graph.model.util.NodeProcessorFactory.class);
		org.nasdanika.graph.model.util.NodeProcessorFactory factory = null;
		for (org.nasdanika.graph.model.util.NodeProcessorFactory candidate: nodeFactories) {
			if (candidate.canHandle(node)) {
				if (factory == null || factory.priority() > candidate.priority()) {
					factory = candidate;
				}
			}
		}
		
		if (factory == null) {
			throw new UnsupportedOperationException("Factory not found for node: " + node); 
		}
		
		return factory.createProcessor(
				config, 
				infoProvider, 
				endpointWiringStageConsumer,
				incomingEndpoints,
				outgoingEndpoints,
				context, 
				progressMonitor);
	}	

}
