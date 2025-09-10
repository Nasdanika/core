package org.nasdanika.graph.message;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

/**
 * A message sent from a {@link Connection} to its target {@link Node}.
 */
public class TargetMessage<C extends Connection,T extends Node,V> extends ElementMessage<T,V,NodeProcessor<T,V>> {

	public TargetMessage(OutgoingConnectionMessage<C,V> parent, T target, V value, NodeProcessor<T,V> processor) {
		super(parent, target, value, processor);
	}

}
