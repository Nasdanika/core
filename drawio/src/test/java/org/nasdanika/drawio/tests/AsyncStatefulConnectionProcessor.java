package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.function.StatefulBiFunctionConnectionProcessor;

public class AsyncStatefulConnectionProcessor implements StatefulBiFunctionConnectionProcessor<String,CompletionStage<String>,String,CompletionStage<String>,ConnectionStateRecord> {
	
	private List<ConnectionStateRecord> history = Collections.synchronizedList(new ArrayList<>());
	private List<ConnectionStateRecord> sourceHistory = Collections.synchronizedList(new ArrayList<>());
	private List<ConnectionStateRecord> targetHistory = Collections.synchronizedList(new ArrayList<>());
	
	@Override
	public void recordSourceState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> sourceHistory.add(new ConnectionStateRecord(input, r)));
	}
	
	@Override
	public void recordTargetState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> targetHistory.add(new ConnectionStateRecord(input, r)));
	}
	
	@Override
	public void recordState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> history.add(new ConnectionStateRecord(input, r)));
	}

	@Override
	public List<ConnectionStateRecord> getHistory(ProgressMonitor progressMonitor) {
		return history;
	}
		
	@Override
	public List<ConnectionStateRecord> getSourceHistory(ProgressMonitor progressMonitor) {
		return sourceHistory;
	}

	@Override
	public List<ConnectionStateRecord> getTargetHistory(ProgressMonitor progressMonitor) {
		return targetHistory;
	}

	@Override
	public CompletionStage<String> sourceApply(String input,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> targetEndpoint,
			List<ConnectionStateRecord> sourceHistory, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "Source apply", input, sourceHistory);
		return targetEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + sourceHistory);
	}

	@Override
	public CompletionStage<String> targetApply(String input,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> sourceEndpoint,
			List<ConnectionStateRecord> targetHistory, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "Target apply", input, targetHistory);		
		return sourceEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + targetHistory);
	}

	@Override
	public CompletionStage<String> apply(String input, List<ConnectionStateRecord> history,	ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "Apply", input, history, sourceHistory, targetHistory);
		return CompletableFuture.completedStage(history.toString());
	}
	
}
