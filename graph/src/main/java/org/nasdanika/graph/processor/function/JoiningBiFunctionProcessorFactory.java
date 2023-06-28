package org.nasdanika.graph.processor.function;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * A processor factory which stores and joins results of enpdoint and client code invocations.
 * It can be used, for example, for computation of node positions in a force layout graph, to build a graph neural network, or to implement a flow activity with multiple inputs.
 * 
 *  @param <H> History value type which is extracted from the argument. For example, in the case of a neural network the argument (T) would be a message, and the history value would be a number to be used as input to the aggregation function.
 *  In some situations the argument itself can be used as history value.
 */
public interface JoiningBiFunctionProcessorFactory<T,U,V,W,R,H> extends BiFunctionProcessorFactory<T,U,V,W,R> {
	
	/**
	 * Provides historical value. Can be extended to provide additional functionality e.g. a timestamp
	 * @param <H>
	 */
	interface History<H> {
		
		H get(ProgressMonitor progressMonitor);
		
	}
	
	History<H> createHistory(T input, ProgressMonitor progressMonitor);
		
	List<History<H>> getSourceHistory(Connection connection, ProgressMonitor progressMonitor);
		
	List<History<H>> getTargetHistory(Connection connection, ProgressMonitor progressMonitor);

	List<History<H>> getIncomingHistory(Node node, Connection incomingConnection, ProgressMonitor progressMonitor);
	
	List<History<H>> getOutgoingHistory(Node node, Connection outgoingConnection, ProgressMonitor progressMonitor);		
		
	@Override
	default U sourceApply(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> connectionProcessorConfig,
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> targetEndpoint) {
		
		History<H> history = createHistory(input, progressMonitor);
		List<History<H>> sourceHistory = getSourceHistory(connectionProcessorConfig.getElement(), progressMonitor);
		sourceHistory.add(history);
		return sourceApply(connectionProcessorConfig, input, progressMonitor, targetEndpoint, sourceHistory);
	}
	
	/**
	 * Source apply with history
	 * @param history History with already added current entry.
	 */
	U sourceApply(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> connectionProcessorConfig,
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> targetEndpoint,
			List<History<H>> sourceHistory);	
	
	@Override
	default U targetApply(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> connectionProcessorConfig,
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint) {
		List<History<H>> targetHistory = getTargetHistory(connectionProcessorConfig.getElement(), progressMonitor);
		targetHistory.add(createHistory(input, progressMonitor));
		return targetApply(connectionProcessorConfig, input, progressMonitor, sourceEndpoint, targetHistory);
	}

	U targetApply(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> connectionProcessorConfig,
			T input, 
			ProgressMonitor progressMonitor, 
			BiFunction<V, ProgressMonitor, W> sourceEndpoint,
			List<History<H>> sourceHistory);
	
	@Override
	default U nodeApply(
			NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> nodeProcessorConfig,
			Connection connection, 
			boolean isIncoming, 
			T input, 
			ProgressMonitor progressMonitor,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> incomingEndpoints,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> outgoingEndpoints,
			Collection<Throwable> failures) {
		
		Map<Connection, List<History<H>>> incomingHistory = new LinkedHashMap<>();
		Node node = nodeProcessorConfig.getElement();
		for (Connection incomingConnection: node.getIncomingConnections()) {
			List<History<H>> ich = getIncomingHistory(node, incomingConnection, progressMonitor);
			if (isIncoming && connection == incomingConnection) {
				ich.add(createHistory(input, progressMonitor));				
			}
			incomingHistory.put(incomingConnection, ich);
		}
		
		Map<Connection, List<History<H>>> outgoingHistory = new LinkedHashMap<>();
		for (Connection outgoingConnection: node.getOutgoingConnections()) {
			List<History<H>> och = getIncomingHistory(node, outgoingConnection, progressMonitor);
			if (!isIncoming && connection == outgoingConnection) {
				och.add(createHistory(input, progressMonitor));				
			}
			outgoingHistory.put(outgoingConnection, och);
		}
		
		return nodeApply(
				connection, 
				isIncoming, 
				input, 
				progressMonitor, 
				incomingEndpoints, 
				outgoingEndpoints, 
				nodeProcessorConfig, 
				failures, 
				incomingHistory, 
				outgoingHistory);
	}
	
	U nodeApply(
			Connection connection, 
			boolean isIncoming, 
			T input, 
			ProgressMonitor progressMonitor,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> incomingEndpoints,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> outgoingEndpoints,
			NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>, R> nodeProcessorConfig,
			Collection<Throwable> failures,
			Map<Connection, List<History<H>>> incomingHistory,
			Map<Connection, List<History<H>>> outgoingHistory);	
}
