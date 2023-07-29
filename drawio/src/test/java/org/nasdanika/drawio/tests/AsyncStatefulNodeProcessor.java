package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.function.StatefulBiFunctionNodeProcessor;

public abstract class AsyncStatefulNodeProcessor implements StatefulBiFunctionNodeProcessor<String,CompletionStage<String>,String,CompletionStage<String>,NodeStateRecord> {

	private NodeStateRecord historyHead;

	@Override
	public void recordIncomingState(Connection activator, String input, CompletionStage<String> result,	List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> {
			synchronized (this) {
				historyHead = new NodeStateRecord(activator, input, r, historyHead, "incoming");
				progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Created incoming history", historyHead);
			}
		});		
	}

	@Override
	public void recordOutgoingState(Connection activator, String input, CompletionStage<String> result,	List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> {
			synchronized (this) {
				historyHead = new NodeStateRecord(activator, input, r, historyHead, "outgoing");
				progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Created outgoing history", historyHead);
			}
		});
	}

	@Override
	public void recordState(String input, CompletionStage<String> result, List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> {
			synchronized (this) {
				historyHead = new NodeStateRecord(null, input, r, historyHead, "apply");
				progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Created apply history", historyHead);
			}
		});
	}

	@Override
	public List<NodeStateRecord> getHistory(ProgressMonitor progressMonitor) {
		return historyHead == null ? Collections.emptyList() : historyHead.toList();
	}

	protected abstract Collection<BiFunction<String, ProgressMonitor, CompletionStage<String>>> getOutgoingEndpoints();
	
	@Override
	public CompletionStage<String> applyIncoming(Connection connection, String input, List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying incoming", connection, input, history);		
		
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

	@Override
	public CompletionStage<String> applyOutgoing(Connection connection, String input, List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying outgoing", connection, input, history);		
		return CompletableFuture.completedStage("Nothing");
	}

	@Override
	public CompletionStage<String> apply(String input, List<NodeStateRecord> history, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Applying ", input, history);		
		
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

}
