package org.nasdanika.drawio;

import java.util.List;

public interface Node extends PageElement {
	
	List<Connection> getInboundConnections();
	
	List<Connection> getOutboundConnections();	

}
