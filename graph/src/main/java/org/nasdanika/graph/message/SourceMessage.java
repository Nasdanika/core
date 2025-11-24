package org.nasdanika.graph.message;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

/**
 * A message sent from a {@link Connection} to the source {@link Node}.
 */
public class SourceMessage<C extends Connection,T extends Node,V> extends ElementMessage<T,V,NodeProcessor<T,V>> {

	public SourceMessage(IncomingConnectionMessage<C,V> parent, NodeProcessor<T,V> processor, V value) {
		super(parent, processor, value);
	}

}
