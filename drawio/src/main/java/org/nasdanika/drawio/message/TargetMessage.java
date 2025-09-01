package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent from a {@link Connection} to its target {@link Node}.
 */
public class TargetMessage<V> extends Message<Node,V> {

	public TargetMessage(OutgoingMessage<V> parent, Node target, V value) {
		super(parent, target, value);
	}

}
