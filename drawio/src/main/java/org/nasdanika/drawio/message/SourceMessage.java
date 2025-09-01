package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent from a {@link Connection} to the source {@link Node}.
 */
public class SourceMessage<V> extends Message<Node,V> {

	public SourceMessage(IncomingMessage<V> parent, Node target, V value) {
		super(parent, target, value);
	}

}
