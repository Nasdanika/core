package org.nasdanika.graph.message;

/**
 * Root message without a parent, type and target.
 * It can be used by client code to pass to client endpoints.
 */
public class RootMessage<V> extends Message<V> {

	public RootMessage(V value) {
		super(null, null, null, value);
	}

}
