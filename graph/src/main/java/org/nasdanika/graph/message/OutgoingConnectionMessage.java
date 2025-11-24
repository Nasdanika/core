package org.nasdanika.graph.message;

import org.nasdanika.graph.Connection;

/**
 * A message sent to {@link Connection} from its source
 */
public class OutgoingConnectionMessage<T extends Connection,V> extends ElementMessage<T,V,ConnectionProcessor<T,V>> {

	public OutgoingConnectionMessage(ElementMessage<?,V,?> parent, ConnectionProcessor<T,V> processor, V value) {
		super(parent, processor, value);
	}

}
