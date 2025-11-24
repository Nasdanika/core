package org.nasdanika.graph.message;

import org.nasdanika.graph.Connection;

/**
 * A message sent to {@link Connection} from its target
 */
public class IncomingConnectionMessage<T extends Connection, V> extends ElementMessage<T,V,ConnectionProcessor<T,V>> {

	public IncomingConnectionMessage(ElementMessage<?,V,?> parent, ConnectionProcessor<T,V> processor, V value) {
		super(parent, processor, value);
	}

}
