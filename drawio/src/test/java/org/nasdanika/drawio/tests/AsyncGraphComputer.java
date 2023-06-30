package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.CompletionStageNopEndpointJoiningBiFunctionProcessorFactory;

public class AsyncGraphComputer implements CompletionStageNopEndpointJoiningBiFunctionProcessorFactory<String, String, Object, String> {

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
	
	private Map<Connection,List<History<String>>> sourceHistory = new ConcurrentHashMap<>();

	@Override
	public List<History<String>> getSourceHistory(Connection connection, ProgressMonitor progressMonitor) {
		return sourceHistory.computeIfAbsent(connection, c -> Collections.synchronizedList(new ArrayList<>()));
	}

	private Map<Connection,List<History<String>>> targetHistory = new ConcurrentHashMap<>();
	
	@Override
	public List<History<String>> getTargetHistory(Connection connection, ProgressMonitor progressMonitor) {
		return targetHistory.computeIfAbsent(connection, c -> Collections.synchronizedList(new ArrayList<>()));
	}
	
	private Map<Node,Map<Connection,List<History<String>>>> incomingHistory = new ConcurrentHashMap<>();

	@Override
	public List<History<String>> getIncomingHistory(Node node, Connection incomingConnection, ProgressMonitor progressMonitor) {
		return incomingHistory.computeIfAbsent(node, n -> new ConcurrentHashMap<>()).computeIfAbsent(incomingConnection, c -> Collections.synchronizedList(new ArrayList<>()));
	}
	
	private Map<Node,Map<Connection,List<History<String>>>> outgoingHistory = new HashMap<>();

	@Override
	public List<History<String>> getOutgoingHistory(Node node, Connection outgoingConnection, ProgressMonitor progressMonitor) {
		return outgoingHistory.computeIfAbsent(node, n -> new ConcurrentHashMap<>()).computeIfAbsent(outgoingConnection, c -> Collections.synchronizedList(new ArrayList<>()));
	}
	
	@Override
	public Object createRegistry(Map<Element, ProcessorInfo<BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object>> registry) {
		return registry;
	}

	@Override
	public CompletionStage<String> sourceApply(
			ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object> connectionProcessorConfig,
			String input, ProgressMonitor progressMonitor,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> targetEndpoint,
			List<History<String>> sourceHistory) {
		
		return targetEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + sourceHistory);
	}

	@Override
	public CompletionStage<String> targetApply(
			ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object> connectionProcessorConfig,
			String input, ProgressMonitor progressMonitor,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> sourceEndpoint,
			List<History<String>> targetHistory) {
		return sourceEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + targetHistory);
	}

	@Override
	public CompletionStage<String> nodeApply(Connection connection, boolean isIncoming, String input,
			ProgressMonitor progressMonitor,
			Map<Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> incomingEndpoints,
			Map<Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> outgoingEndpoints,
			NodeProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, Object> nodeProcessorConfig,
			Map<Connection, List<History<String>>> incomingHistory,
			Map<Connection, List<History<String>>> outgoingHistory) {
		
		StringBuilder sb = new StringBuilder();
		List<CompletionStage<String>> rcs = new ArrayList<>();
		for (BiFunction<String, ProgressMonitor, CompletionStage<String>> oe: outgoingEndpoints.values()) {
			CompletionStage<String> oer = oe.apply(input, progressMonitor);
			rcs.add(oer);
			oer.thenAccept(r -> sb.append(r).append(" "));
		}
		
		Function<Void,String> rg = r -> sb.toString();		
		CompletableFuture<?>[] cfs = rcs.toArray(new CompletableFuture[rcs.size()]);
		return CompletableFuture.allOf(cfs).thenApply(rg);
	}

}
