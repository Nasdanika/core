package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.processor.function.HistoryBiFunctionConnectionProcessor;

public class AsyncHistoryConnectionProcessor implements HistoryBiFunctionConnectionProcessor<String,CompletionStage<String>,String,CompletionStage<String>,String> {

	@Override
	public Supplier<String> createHistory(String input, ProgressMonitor progressMonitor) {
		progressMonitor.worked(1, "[ " + Thread.currentThread().getName() + "] Created history", input);
		return Supplier.from(input, "History supplier of " + input);
	}
	
	private List<Supplier<String>> sourceHistory = Collections.synchronizedList(new ArrayList<>());

	@Override
	public List<Supplier<String>> getSourceHistory(ProgressMonitor progressMonitor) {
		return sourceHistory;
	}
		
	private List<Supplier<String>> targetHistory = Collections.synchronizedList(new ArrayList<>());

	@Override
	public List<Supplier<String>> getTargetHistory(ProgressMonitor progressMonitor) {
		return targetHistory;
	}

	@Override
	public CompletionStage<String> sourceApply(
			String input, 
			ProgressMonitor progressMonitor,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> targetEndpoint,
			List<Supplier<String>> sourceHistory) {
		progressMonitor.worked(1, "Source apply", input, sourceHistory);
		return targetEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + sourceHistory);
	}

	@Override
	public CompletionStage<String> targetApply(
			String input, ProgressMonitor progressMonitor,
			BiFunction<String, ProgressMonitor, CompletionStage<String>> sourceEndpoint,
			List<Supplier<String>> targetHistory) {
		progressMonitor.worked(1, "Target apply", input, targetHistory);		
		return sourceEndpoint.apply(input, progressMonitor).thenApplyAsync(r ->  "[" + Thread.currentThread().getName() + "] " + r + " " + targetHistory);
	}

	@Override
	public CompletionStage<String> apply(
			String input, 
			ProgressMonitor progressMonitor,
			List<Supplier<String>> sourceHistory, 
			List<Supplier<String>> targetHistory) {
		progressMonitor.worked(1, "Source apply", input, sourceHistory, targetHistory);
		return CompletableFuture.completedStage(sourceHistory + " " + targetHistory);
	}
	
}
