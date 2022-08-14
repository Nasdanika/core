package org.nasdanika.drawio;

import java.util.List;

public interface Node extends Layer, org.nasdanika.graph.Node {
	
	List<Connection> getIncomingConnections();
	
	List<Connection> getOutgoingConnections();	
		
	Rectangle getGeometry();

}
