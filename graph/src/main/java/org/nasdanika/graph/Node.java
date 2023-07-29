package org.nasdanika.graph;

import java.util.Collection;

public interface Node extends Element {
	
	Collection<? extends Connection> getIncomingConnections();
	
	Collection<? extends Connection> getOutgoingConnections();	

}
