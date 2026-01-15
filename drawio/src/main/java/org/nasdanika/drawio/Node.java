package org.nasdanika.drawio;

import java.util.List;

public interface Node extends Layer<Node>, LayerElement<Node>, org.nasdanika.graph.Node {
	
	@Override
	List<Connection> getIncomingConnections();
	
	@Override
	List<Connection> getOutgoingConnections();	
		
	Geometry getGeometry();

}
