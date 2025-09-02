package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent from a {@link Connection} to the source {@link Node}.
 */
public class SourceMessage<V> extends ElementMessage<Node,V,NodeProcessor<V>> {

	public SourceMessage(IncomingMessage<V> parent, Node target, V value, NodeProcessor<V> processor) {
		super(parent, target, value, processor);
	}

}
