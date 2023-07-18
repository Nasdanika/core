package org.nasdanika.graph.tests;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory;

public class BiFunctionProcessorFactoryImpl extends BiFunctionProcessorFactory<Object, Object, Object, Object> {
	
	private boolean passThrough;

	public BiFunctionProcessorFactoryImpl(boolean passThrough) {
		this.passThrough = passThrough;
	}

	@Override
	protected BiFunctionProcessorFactory.ConnectionProcessor<Object, Object, Object, Object> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> connectionProcessorConfig,
			boolean parallel,
			Function<Element, CompletionStage<BiFunction<Object, ProgressMonitor, Object>>> processorProvider,
			Consumer<CompletionStage<?>> stageConsumer, 
			ProgressMonitor progressMonitor) {
		return new BiFunctionConnectionProcessorImpl(connectionProcessorConfig, parallel, processorProvider, stageConsumer);
	}

	@Override
	protected BiFunctionProcessorFactory.NodeProcessor<Object, Object, Object, Object> createNodeProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> nodeProcessorConfig,
			boolean parallel,
			Function<Element, CompletionStage<BiFunction<Object, ProgressMonitor, Object>>> processorProvider,
			Consumer<CompletionStage<?>> stageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		return new BiFunctionNodeProcessor(
				nodeProcessorConfig,
				parallel,
				processorProvider,
				stageConsumer,
				incomingEndpoints,
				outgoingEndpoints,
				passThrough);
	}

}
