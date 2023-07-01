package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.function.HistoryBiFunctionNodeProcessor;

public abstract class AsyncHistoryNodeProcessor implements HistoryBiFunctionNodeProcessor<String,CompletionStage<String>,String,CompletionStage<String>,String> {

	@Override
	public Supplier<String> createHistory(String input, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Created history", input);
		return Supplier.from(input, "History supplier of " + input);
	}
	
	private Map<Connection,List<Supplier<String>>> incomingHistory = new ConcurrentHashMap<>();

	@Override
	public List<Supplier<String>> getIncomingHistory(Connection incomingConnection, ProgressMonitor progressMonitor) {
		return incomingHistory.computeIfAbsent(incomingConnection, c -> Collections.synchronizedList(new ArrayList<>()));
	}
	
	private Map<Connection,List<Supplier<String>>> outgoingHistory = new ConcurrentHashMap<>();

	@Override
	public List<Supplier<String>> getOutgoingHistory(Connection outgoingConnection, ProgressMonitor progressMonitor) {
		return outgoingHistory.computeIfAbsent(outgoingConnection, c -> Collections.synchronizedList(new ArrayList<>()));
	}

	@Override
	public CompletionStage<String> applyIncoming(
			Connection connection,
			String input, 
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<String>>> incomingHistory,
			Map<Connection, List<Supplier<String>>> outgoingHistory) {

		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying incoming", connection, input, incomingHistory, outgoingHistory);		
		
		StringBuilder sb = new StringBuilder();
		List<CompletionStage<String>> rcs = new ArrayList<>();
		for (BiFunction<String, ProgressMonitor, CompletionStage<String>> oe: getOutgoingEndpoints()) {
			CompletionStage<String> oer = oe.apply(input, progressMonitor);
			rcs.add(oer);
			oer.thenAccept(r -> sb.append(r).append(" "));
		}
		
		Function<Void,String> rg = r -> sb.toString();		
		CompletableFuture<?>[] cfs = rcs.toArray(new CompletableFuture[rcs.size()]);
		return CompletableFuture.allOf(cfs).thenApply(rg);
	}

	protected abstract List<BiFunction<String, ProgressMonitor, CompletionStage<String>>> getOutgoingEndpoints();

	@Override
	public CompletionStage<String> applyOutgoing(
			Connection connection, 
			String input, 
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<String>>> incomingHistory,
			Map<Connection, List<Supplier<String>>> outgoingHistory) {

		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying outgoing", connection, input, incomingHistory, outgoingHistory);		
		return CompletableFuture.completedStage("Nothing");
	}

	@Override
	public CompletionStage<String> apply(
			String input,
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<String>>> incomingHistory,
			Map<Connection, List<Supplier<String>>> outgoingHistory) {

		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying ", input, incomingHistory, outgoingHistory);		
		
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
