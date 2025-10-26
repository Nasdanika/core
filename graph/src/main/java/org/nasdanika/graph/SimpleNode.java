package org.nasdanika.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

/**
 * A node implementation with synchronized collections of incoming and outgoing connections. 
 * Outgoing connections are treated as node children.
 * Treats 
 */
public class SimpleNode implements Node {
	
	private Collection<org.nasdanika.graph.Connection> incomingConnections = Collections.synchronizedCollection(new ArrayList<>());
	private Collection<org.nasdanika.graph.Connection> outgoingConnections = Collections.synchronizedCollection(new ArrayList<>());
	
	private Supplier<Collection<? extends Element>> childrenSupplier;

	public SimpleNode() {
		this(Collections.emptyList());
	}

	public SimpleNode(Collection<? extends Element> children) {
		this(() -> children == null ? Collections.emptyList() : children);
	}

	public SimpleNode(Supplier<Collection<? extends Element>> childrenSupplier) {
		this.childrenSupplier = childrenSupplier == null ? () -> Collections.emptyList() : childrenSupplier;
	}
	
			
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
		Collection<Element> children = new ArrayList<>(getOutgoingConnections());
		Collection<? extends Element> sc = childrenSupplier.get();
		if (sc != null) {
			children.addAll(sc);
		}
		return children;
	}	
	
}
