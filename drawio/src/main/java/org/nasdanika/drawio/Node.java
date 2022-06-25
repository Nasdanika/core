package org.nasdanika.drawio;

import java.util.List;

public interface Node extends Layer {
	
	List<Connection> getInboundConnections();
	
	List<Connection> getOutboundConnections();	
		
	Rectangle getGeometry();

}
