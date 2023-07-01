package org.nasdanika.graph.processor.function;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

public interface HistoryBiFunctionNodeProcessor<T,U,V,W,H> extends BiFunctionProcessorFactory.NodeProcessor<T, U, V, W> {
	
	Supplier<H> createHistory(T input, ProgressMonitor progressMonitor);
		
	List<Supplier<H>> getIncomingHistory(Connection incomingConnection, ProgressMonitor progressMonitor);
	
	List<Supplier<H>> getOutgoingHistory(Connection outgoingConnection, ProgressMonitor progressMonitor);
	
	Node getNode();
	
	@Override
	default U applyIncoming(Connection connection, T input, ProgressMonitor progressMonitor) {		
		Map<Connection, List<Supplier<H>>> incomingHistory = new LinkedHashMap<>();
		for (Connection incomingConnection: getNode().getIncomingConnections()) {
			List<Supplier<H>> ich = getIncomingHistory(incomingConnection, progressMonitor);
			if (connection == incomingConnection) {
				synchronized (ich) {				
					ich.add(createHistory(input, progressMonitor));
				}
			}
			incomingHistory.put(incomingConnection, ich);
		}
		
		Map<Connection, List<Supplier<H>>> outgoingHistory = new LinkedHashMap<>();
		for (Connection outgoingConnection: getNode().getOutgoingConnections()) {
			List<Supplier<H>> och = getOutgoingHistory(outgoingConnection, progressMonitor);
			outgoingHistory.put(outgoingConnection, och);
		}
		
		return applyIncoming(
				connection, 
				input, 
				progressMonitor, 
				incomingHistory, 
				outgoingHistory);
	}
	
	@Override
	default U applyOutgoing(Connection connection, T input, ProgressMonitor progressMonitor) {
		Map<Connection, List<Supplier<H>>> incomingHistory = new LinkedHashMap<>();
		for (Connection incomingConnection: getNode().getIncomingConnections()) {
			List<Supplier<H>> ich = getIncomingHistory(incomingConnection, progressMonitor);
			incomingHistory.put(incomingConnection, ich);
		}
		
		Map<Connection, List<Supplier<H>>> outgoingHistory = new LinkedHashMap<>();
		for (Connection outgoingConnection: getNode().getOutgoingConnections()) {
			List<Supplier<H>> och = getOutgoingHistory(outgoingConnection, progressMonitor);
			if (connection == outgoingConnection) {
				synchronized (och) {
					och.add(createHistory(input, progressMonitor));
				}
			}
			outgoingHistory.put(outgoingConnection, och);
		}
		
		return applyOutgoing(
				connection, 
				input, 
				progressMonitor, 
				incomingHistory, 
				outgoingHistory);
	}
	
	@Override
	default U apply(T input, ProgressMonitor progressMonitor) {
		Map<Connection, List<Supplier<H>>> incomingHistory = new LinkedHashMap<>();
		for (Connection incomingConnection: getNode().getIncomingConnections()) {
			List<Supplier<H>> ich = getIncomingHistory(incomingConnection, progressMonitor);
			incomingHistory.put(incomingConnection, ich);
		}
		
		Map<Connection, List<Supplier<H>>> outgoingHistory = new LinkedHashMap<>();
		for (Connection outgoingConnection: getNode().getOutgoingConnections()) {
			List<Supplier<H>> och = getOutgoingHistory(outgoingConnection, progressMonitor);
			outgoingHistory.put(outgoingConnection, och);
		}
		
		return apply(
				input, 
				progressMonitor, 
				incomingHistory, 
				outgoingHistory);
	}
	
	U applyIncoming(
			Connection connection, 
			T input, 
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<H>>> incomingHistory,
			Map<Connection, List<Supplier<H>>> outgoingHistory);	
	
	U applyOutgoing(
			Connection connection, 
			T input, 
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<H>>> incomingHistory,
			Map<Connection, List<Supplier<H>>> outgoingHistory);
		
	U apply(
			T input, 
			ProgressMonitor progressMonitor,
			Map<Connection, List<Supplier<H>>> incomingHistory,
			Map<Connection, List<Supplier<H>>> outgoingHistory);	
	
}
