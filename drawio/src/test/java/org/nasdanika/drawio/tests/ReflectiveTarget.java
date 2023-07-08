package org.nasdanika.drawio.tests;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
//import org.nasdanika.common.Reflector.Factory;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactory.ConnectionProcessorFactory;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactory.NodeProcessorFactory;

//@Factory("Some bred for testing")
public class ReflectiveTarget {
	
	@ConnectionProcessorFactory
	public ConnectionProcessor<String, CompletionStage<String>, String, CompletionStage<String>> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object> connectionProcessorConfig,
			ProgressMonitor progressMonitor) {
		return new AsyncHistoryConnectionProcessor();
	}

	@NodeProcessorFactory
	public NodeProcessor<String, CompletionStage<String>, String, CompletionStage<String>> createNodeProcessor(
			NodeProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object> nodeProcessorConfig,
			Map<org.nasdanika.graph.Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> incomingEndpoints,
			Map<org.nasdanika.graph.Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		return new AsyncHistoryNodeProcessor() {
			
			@Override
			public org.nasdanika.graph.Node getNode() {
				return nodeProcessorConfig.getElement();
			}
			
			@Override
			protected Collection<BiFunction<String, ProgressMonitor, CompletionStage<String>>> getOutgoingEndpoints() {
				return outgoingEndpoints.values();
			}
		};
	}	

}
