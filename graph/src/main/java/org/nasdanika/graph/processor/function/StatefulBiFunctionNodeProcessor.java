package org.nasdanika.graph.processor.function;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;

public interface StatefulBiFunctionNodeProcessor<T,U,V,W,S> extends BiFunctionProcessorFactory.NodeProcessor<T, U, V, W> {
		
	/**
	 * Creates state and adds to history
	 * @param activator
	 * @param input
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	void recordIncomingState(Connection activator, T input, U result, List<S> history, ProgressMonitor progressMonitor);
	
	/**
	 * Creates state and adds to history
	 * @param activator
	 * @param input
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	void recordOutgoingState(Connection activator, T input, U result, List<S> history, ProgressMonitor progressMonitor);
	
	/**
	 * Creates state and adds to history
	 * @param input
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	void recordState(T input, U result, List<S> history, ProgressMonitor progressMonitor);
		
	List<S> getHistory(ProgressMonitor progressMonitor);
	
	@Override
	default U applyIncoming(Connection connection, T input, ProgressMonitor progressMonitor) {
		List<S> history = getHistory(progressMonitor);
		U result = applyIncoming(connection, input,	history, progressMonitor);
		recordIncomingState(connection, input, result, history, progressMonitor);
		return result;
	}
	
	@Override
	default U applyOutgoing(Connection connection, T input, ProgressMonitor progressMonitor) {
		List<S> history = getHistory(progressMonitor);
		U result = applyOutgoing(connection, input,	history, progressMonitor);
		recordOutgoingState(connection, input, result, history, progressMonitor);
		return result;
	}
	
	@Override
	default U apply(T input, ProgressMonitor progressMonitor) {
		List<S> history = getHistory(progressMonitor);
		U result = apply(input,	history, progressMonitor);
		recordState(input, result, history, progressMonitor);
		return result;
	}
	
	U applyIncoming(
			Connection connection, 
			T input, 
			List<S> history, 
			ProgressMonitor progressMonitor);	
	
	U applyOutgoing(
			Connection connection, 
			T input, 
			List<S> history, 
			ProgressMonitor progressMonitor);
		
	U apply(T input, List<S> history, ProgressMonitor progressMonitor);	
	
}
