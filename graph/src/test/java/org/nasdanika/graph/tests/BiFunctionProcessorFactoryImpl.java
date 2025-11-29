package org.nasdanika.graph.tests;

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
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory;

public class BiFunctionProcessorFactoryImpl extends BiFunctionProcessorFactory<Object, Object, Object, Object, String> {
	
	private boolean passThrough;

	public BiFunctionProcessorFactoryImpl(boolean passThrough) {
		this.passThrough = passThrough;
	}

	@Override
	protected ConnectionProcessor<Object, Object, Object, Object> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>, String> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>,BiFunction<Object, ProgressMonitor, Object>,String,BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {

		return new BiFunctionConnectionProcessorImpl(connectionProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer);
	}

	@Override
	protected NodeProcessor<Object, Object, Object, Object> createNodeProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>, String> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>,BiFunction<Object, ProgressMonitor, Object>,String,BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {

		return new BiFunctionNodeProcessor(
			nodeProcessorConfig,
			parallel,
			infoProvider,
			endpointWiringStageConsumer,
			incomingEndpoints,
			outgoingEndpoints,
			passThrough);
	}

}
