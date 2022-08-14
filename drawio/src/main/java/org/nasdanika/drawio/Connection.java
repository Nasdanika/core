package org.nasdanika.drawio;

public interface Connection extends ModelElement, org.nasdanika.graph.Connection {
	
	@Override
	Node getSource();
	
	@Override
	Node getTarget();

}
