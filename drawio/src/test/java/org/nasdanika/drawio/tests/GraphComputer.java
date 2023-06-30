package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.NopEndpointJoiningBiFunctionProcessorFactory;

public class GraphComputer implements NopEndpointJoiningBiFunctionProcessorFactory<String, String, Object, String> {

	@Override
	public History<String> createHistory(String input, ProgressMonitor progressMonitor) {
		return new History<String>() {
			
			@Override
			public String get(ProgressMonitor progressMonitor) {
				return input;
			}
			
			@Override
			public String toString() {
				return "History: " + input;
			}
			
		};
	}
	
	private Map<Connection,List<History<String>>> sourceHistory = new HashMap<>();

	@Override
	public List<History<String>> getSourceHistory(Connection connection, ProgressMonitor progressMonitor) {
		return sourceHistory.computeIfAbsent(connection, c -> new ArrayList<>());
	}

	private Map<Connection,List<History<String>>> targetHistory = new HashMap<>();
	
	@Override
	public List<History<String>> getTargetHistory(Connection connection, ProgressMonitor progressMonitor) {
		return targetHistory.computeIfAbsent(connection, c -> new ArrayList<>());
	}
	
	private Map<Node,Map<Connection,List<History<String>>>> incomingHistory = new HashMap<>();

	@Override
	public List<History<String>> getIncomingHistory(Node node, Connection incomingConnection, ProgressMonitor progressMonitor) {
		return incomingHistory.computeIfAbsent(node, n -> new HashMap<>()).computeIfAbsent(incomingConnection, c -> new ArrayList<>());
	}
	
	private Map<Node,Map<Connection,List<History<String>>>> outgoingHistory = new HashMap<>();

	@Override
	public List<History<String>> getOutgoingHistory(Node node, Connection outgoingConnection, ProgressMonitor progressMonitor) {
		return outgoingHistory.computeIfAbsent(node, n -> new HashMap<>()).computeIfAbsent(outgoingConnection, c -> new ArrayList<>());
	}

	@Override
	public String sourceApply(
			ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, Object> connectionProcessorConfig,
			String input, 
			ProgressMonitor progressMonitor, 
			BiFunction<String, ProgressMonitor, String> targetEndpoint,
			List<History<String>> sourceHistory) {
		
		return targetEndpoint.apply(input, progressMonitor) + " " + sourceHistory;
	}

	@Override
	public String targetApply(
			ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, Object> connectionProcessorConfig,
			String input, 
			ProgressMonitor progressMonitor, 
			BiFunction<String, ProgressMonitor, String> sourceEndpoint,
			List<History<String>> sourceHistory) {

		return sourceEndpoint.apply(input, progressMonitor) + " " + sourceHistory;
	}

	@Override
	public String nodeApply(Connection connection, boolean isIncoming, String input, ProgressMonitor progressMonitor,
			Map<Connection, BiFunction<String, ProgressMonitor, String>> incomingEndpoints,
			Map<Connection, BiFunction<String, ProgressMonitor, String>> outgoingEndpoints,
			NodeProcessorConfig<BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, BiFunction<String, ProgressMonitor, String>, Object> nodeProcessorConfig,
			Collection<Throwable> failures, Map<Connection, List<History<String>>> incomingHistory,
			Map<Connection, List<History<String>>> outgoingHistory) {
		
		StringBuilder sb = new StringBuilder();		
		for (BiFunction<String, ProgressMonitor, String> oe: outgoingEndpoints.values()) {
			sb.append(oe.apply(input, progressMonitor)).append(" ");			
		}		
		
		return sb.toString() + " " + incomingHistory + " " + outgoingHistory;
	}

	@Override
	public Map<Element, ProcessorInfo<BiFunction<String, ProgressMonitor, String>, Object>> createRegistry(Map<Element, ProcessorInfo<BiFunction<String, ProgressMonitor, String>, Object>> registry) {
		return registry;
	}

}
