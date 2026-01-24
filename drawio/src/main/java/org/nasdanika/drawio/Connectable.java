package org.nasdanika.drawio;

import java.util.List;

/**
 * Source and target of {@link Connection}s.
 *  Base interface for {@link ConnectionPoint} and {@link Node}
 */
public interface Connectable extends org.nasdanika.graph.Node {
	
	@Override
	List<Connection> getIncomingConnections();
	
	@Override
	List<Connection> getOutgoingConnections();

}
