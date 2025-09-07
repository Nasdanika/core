package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Connection;

/**
 * A message sent to {@link Connection} from its source
 */
public class OutgoingConnectionMessage<V> extends ElementMessage<Connection,V,ConnectionProcessor<V>> {

	public OutgoingConnectionMessage(ElementMessage<?,V,?> parent, Connection target, V value, ConnectionProcessor<V> processor) {
		super(parent, target, value, processor);
	}

}
