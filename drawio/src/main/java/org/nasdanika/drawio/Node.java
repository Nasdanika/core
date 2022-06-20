package org.nasdanika.drawio;

import java.util.List;

public interface Node extends ModelElement {
	
	List<Connection> getInboundConnections();
	
	List<Connection> getOutboundConnections();	
	
	List<ModelElement> getChildren();	
	
	Node createChild();

}
