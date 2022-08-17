package org.nasdanika.drawio;

public interface Connection extends LayerElement, org.nasdanika.graph.Connection {
	
	@Override
	Node getSource();
	
	@Override
	Node getTarget();

}
