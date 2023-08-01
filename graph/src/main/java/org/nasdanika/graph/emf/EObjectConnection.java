package org.nasdanika.graph.emf;

public interface EObjectConnection extends org.nasdanika.graph.Connection {
	
	@Override
	EObjectNode getSource();
	
	@Override
	EObjectNode getTarget();

}
