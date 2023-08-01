package org.nasdanika.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A node implementation with synchronized collections of incoming and outgoing connections. 
 * Outgoing connections are treated as node children.
 * Treats 
 */
public class SimpleNode implements Node {
	
	private Collection<org.nasdanika.graph.Connection> incomingConnections = Collections.synchronizedCollection(new ArrayList<>());
	private Collection<org.nasdanika.graph.Connection> outgoingConnections = Collections.synchronizedCollection(new ArrayList<>());
			
	@Override
	public Collection<? extends Connection> getIncomingConnections() {
		return incomingConnections;
	}
	@Override
	public Collection<? extends Connection> getOutgoingConnections() {
		return outgoingConnections;
	}
	
	@Override
	public Collection<? extends Element> getChildren() {
		return getOutgoingConnections();
	}	

}
