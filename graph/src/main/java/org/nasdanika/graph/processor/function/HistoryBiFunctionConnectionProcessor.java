package org.nasdanika.graph.processor.function;

import java.util.List;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

public interface HistoryBiFunctionConnectionProcessor<T,U,V,W,H> extends BiFunctionProcessorFactory.ConnectionProcessor<T, U, V, W> {
	
	Supplier<H> createHistory(T input, ProgressMonitor progressMonitor);
		
	List<Supplier<H>> getSourceHistory(ProgressMonitor progressMonitor);
		
	List<Supplier<H>> getTargetHistory(ProgressMonitor progressMonitor);
	
	@Override
	default U sourceApply(T input, ProgressMonitor progressMonitor, BiFunction<V, ProgressMonitor, W> targetEndpoint) {
		Supplier<H> history = createHistory(input, progressMonitor);
		List<Supplier<H>> sourceHistory = getSourceHistory(progressMonitor);
		synchronized (sourceHistory) {
			sourceHistory.add(history);
		}
		return sourceApply(input, progressMonitor, targetEndpoint, sourceHistory);
	}
	
	/**
	 * Source apply with history
	 * @param history History with already added current entry.
	 */
	U sourceApply(
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> targetEndpoint,
			List<Supplier<H>> sourceHistory);	
	
	@Override
	default U targetApply(
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint) {
		List<Supplier<H>> targetHistory = getTargetHistory(progressMonitor);
		synchronized (targetHistory) {
			targetHistory.add(createHistory(input, progressMonitor));
		}
		return targetApply(input, progressMonitor, sourceEndpoint, targetHistory);
	}

	U targetApply(
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint,
			List<Supplier<H>> targetHistory);
	
	@Override
	default U apply(T input, ProgressMonitor progressMonitor) {
		List<Supplier<H>> sourceHistory = getSourceHistory(progressMonitor);
		List<Supplier<H>> targetHistory = getTargetHistory(progressMonitor);
		return apply(input, progressMonitor, sourceHistory, targetHistory);
	}
	
	U apply(
			T input, 
			ProgressMonitor progressMonitor,
			List<Supplier<H>> sourceHistory,
			List<Supplier<H>> targetHistory);
	
}
