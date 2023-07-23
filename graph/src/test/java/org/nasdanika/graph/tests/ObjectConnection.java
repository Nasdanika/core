package org.nasdanika.graph.tests;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

public class ObjectConnection<T> implements org.nasdanika.graph.Connection {
	
	private Node source;
	private Node target;
	private T value;

	public ObjectConnection(Node source, Node target, T value) {
		this.source = source;
		this.target = target;
		this.value = value;
		source.getOutgoingConnections().add(this);
		target.getIncomingConnections().add(this);
		System.out.println("Connection[" + value + "] " + source + " -> " + target);
	}

	@Override
	public Node getSource() {
		return source;
	}

	@Override
	public Node getTarget() {
		return target;
	}
	
	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + value + "] " + source + " -> " + target;
	}

	@Override
	public <V> V accept(BiFunction<? super Element, Map<? extends Element, V>, V> visitor) {
		return visitor.apply(this, Collections.emptyMap());
	}	
	
}
