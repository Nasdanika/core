package org.nasdanika.graph.processor.function;

import java.util.List;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;

public interface StatefulBiFunctionConnectionProcessor<T,U,V,W,S> extends BiFunctionProcessorFactory.ConnectionProcessor<T, U, V, W> {
	
	/**
	 * Creates a state and adds to history
	 * @param input
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	void recordSourceState(T input, U result, ProgressMonitor progressMonitor);
	void recordTargetState(T input, U result, ProgressMonitor progressMonitor);
	void recordState(T input, U result, ProgressMonitor progressMonitor);
		
	/**
	 * History of processor invocations
	 * @param progressMonitor
	 * @return
	 */
	List<S> getHistory(ProgressMonitor progressMonitor);
	
	@Override
	default U sourceApply(T input, ProgressMonitor progressMonitor, BiFunction<V, ProgressMonitor, W> targetEndpoint) {
		U result = sourceApply(input, targetEndpoint, getHistory(progressMonitor), progressMonitor);
		recordSourceState(input, result, progressMonitor);		
		return result;
	}
	
	/**
	 * Source apply with history
	 * @param history History.
	 */
	U sourceApply(
			T input, 
			BiFunction<V, ProgressMonitor, W> targetEndpoint,
			List<S> history,			
			ProgressMonitor progressMonitor);	
	
	@Override
	default U targetApply(
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint) {
		U result = targetApply(input, sourceEndpoint, getHistory(progressMonitor), progressMonitor);
		recordTargetState(input, result, progressMonitor);		
		return result;
	}

	U targetApply(
			T input, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint,
			List<S> history,			
			ProgressMonitor progressMonitor); 
	
	@Override
	default U apply(T input, ProgressMonitor progressMonitor) {
		U result = apply(input, getHistory(progressMonitor), progressMonitor);
		recordState(input, result, progressMonitor);		
		return result;
	}
	
	U apply(T input, List<S> history, ProgressMonitor progressMonitor);
	
}
