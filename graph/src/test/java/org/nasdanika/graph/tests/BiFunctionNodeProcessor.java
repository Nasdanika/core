package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;

public class BiFunctionNodeProcessor implements NodeProcessor<Object, Object, Object, Object> {
	
	boolean passThrough;
	private NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config;
	private Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints;
	private Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints;
	
	public BiFunctionNodeProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>,BiFunction<Object, ProgressMonitor, Object>,BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			boolean passThrough) {
		
		this.config = nodeProcessorConfig;
		this.passThrough = passThrough;
		this.incomingEndpoints = incomingEndpoints;
		this.outgoingEndpoints = outgoingEndpoints;
	}	

	@Override
	public Object apply(Object arg, ProgressMonitor progressMonitor) {
		int counter = 0; 

		assertEquals(config.getIncomingSynapses().size(), incomingEndpoints.size());
		assertEquals(config.getOutgoingSynapses().size(), outgoingEndpoints.size());
		
		for (Entry<Connection, BiFunction<Object, ProgressMonitor, Object>> ie: incomingEndpoints.entrySet()) {			
			Object ret = ie.getValue().apply(config.getElement(), progressMonitor);			
			assertEquals(passThrough ? ie.getKey().getSource() : ie.getKey(), ret);
			++counter;
		}		
		
		for (Entry<Connection, BiFunction<Object, ProgressMonitor, Object>> oe: outgoingEndpoints.entrySet()) {			
			Object ret = oe.getValue().apply(config.getElement(), progressMonitor);			
			assertEquals(passThrough ? oe.getKey().getTarget() : oe.getKey() , ret);
			++counter;
		}		
		
		return counter;
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
