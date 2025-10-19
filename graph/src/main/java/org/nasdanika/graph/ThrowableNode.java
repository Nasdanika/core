package org.nasdanika.graph;

public class ThrowableNode<T extends Throwable> extends ObjectNode<T> {

	public ThrowableNode(T value) {
		super(value);
	}

}
