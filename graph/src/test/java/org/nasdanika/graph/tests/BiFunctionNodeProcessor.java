package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;

public class BiFunctionNodeProcessor implements NodeProcessor<Object, Object, Object, Object> {
	
	boolean passThrough;
	private NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config;
	
	public BiFunctionNodeProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> nodeProcessorConfig,
			boolean parallel,
			Function<Element, CompletionStage<BiFunction<Object, ProgressMonitor, Object>>> processorProvider,
			Consumer<CompletionStage<?>> stageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			boolean passThrough) {
		
		this.config = nodeProcessorConfig;
		this.passThrough = passThrough;
	}	

	@Override
	public Object apply(Object arg, ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object applyIncoming(Connection connection, Object input, ProgressMonitor progressMonitor) {
		assertEquals(passThrough ? connection.getSource() : connection, input);
		return config.getElement();
	}

	@Override
	public Object applyOutgoing(Connection connection, Object input, ProgressMonitor progressMonitor) {
		assertEquals(passThrough ? connection.getTarget() : connection, input);
		return config.getElement();
	}

}
