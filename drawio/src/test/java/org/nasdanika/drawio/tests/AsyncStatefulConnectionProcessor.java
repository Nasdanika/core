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
	
	@Override
	public void recordSourceState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> history.add(new ConnectionStateRecord("source", input, r)));
	}
	
	@Override
	public void recordTargetState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> history.add(new ConnectionStateRecord("target", input, r)));
	}
	
	@Override
	public void recordState(String input, CompletionStage<String> result, ProgressMonitor progressMonitor) {
		result.thenAccept(r -> history.add(new ConnectionStateRecord("processor", input, r)));
	}

	@Override
	public List<ConnectionStateRecord> getHistory(ProgressMonitor progressMonitor) {
		return history;
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
		progressMonitor.worked(1, "Apply", input, history);
		return CompletableFuture.completedStage(history.toString());
	}
	
}
