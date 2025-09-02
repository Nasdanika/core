package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent from a {@link Connection} to its target {@link Node}.
 */
public class TargetMessage<V> extends ElementMessage<Node,V,NodeProcessor<V>> {

	public TargetMessage(OutgoingMessage<V> parent, Node target, V value, NodeProcessor<V> processor) {
		super(parent, target, value, processor);
	}

}
