package org.nasdanika.graph.message;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Provider of message values
 * @param <V>
 */
public interface MessageValueProvider<V> {
	
	V childValue(V messageValue, Element parent, Element child);

	V sourceValue(V messageValue, Node node, Connection outgoingConnection);

	V targetValue(V messageValue, Node node, Connection incomingConnection);
	
	V incomingValue(V messageValue, Connection connection);

	V outgoingValue(V messageValue, Connection connection);
	
}
