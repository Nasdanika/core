package org.nasdanika.graph;

import java.util.Collection;

public interface Node extends Element {
	
	Collection<Connection> getIncomingConnections();
	
	Collection<Connection> getOutgoingConnections();	

}
