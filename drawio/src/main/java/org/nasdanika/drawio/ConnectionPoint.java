package org.nasdanika.drawio;

public interface ConnectionPoint extends Connectable, ConnectionPointSpec, org.nasdanika.graph.Node {
	
	Node getNode();

}
