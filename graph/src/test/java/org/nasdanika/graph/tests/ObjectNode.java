package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

public class ObjectNode<T> implements Node {
	
	private static Map<Object, Node> instances = new ConcurrentHashMap<>();
	
	private T value;
	private Collection<Connection> incomingConnections = Collections.synchronizedCollection(new ArrayList<>());
	private Collection<Connection> outgoingConnections = Collections.synchronizedCollection(new ArrayList<>());
	private RuntimeException onCreation;

	public ObjectNode(T value) {
		this.value = value;
		this.onCreation = new RuntimeException();
		System.out.println("Node[" + value + "] " + Thread.currentThread().getName());
		Node prev = instances.put(value, this);
		if (prev != null) {
			IllegalStateException illegalStateException = new IllegalStateException("Duplicate for " + value + " " + Integer.toString(System.identityHashCode(prev), Character.MAX_RADIX) + " " + Integer.toString(System.identityHashCode(this), Character.MAX_RADIX), ((ObjectNode) prev).onCreation);
			throw illegalStateException;
		}
	}
	
	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + value + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ObjectNode<T> other = (ObjectNode<T>) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public <V> V accept(BiFunction<? super Element, Map<? extends Element, V>, V> visitor) {
		return visitor.apply(this, Collections.emptyMap());
	}

	@Override
	public Collection<Connection> getIncomingConnections() {
		return incomingConnections;
	}

	@Override
	public Collection<Connection> getOutgoingConnections() {
		return outgoingConnections;
	}

}
