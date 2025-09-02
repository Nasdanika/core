package org.nasdanika.drawio.message;

import org.nasdanika.drawio.Connection;

/**
 * A message sent to {@link Connection} from its source
 */
public class OutgoingMessage<V> extends ElementMessage<Connection,V,ConnectionProcessor<V>> {

	public OutgoingMessage(ElementMessage<?,V,?> parent, Connection target, V value, ConnectionProcessor<V> processor) {
		super(parent, target, value, processor);
	}

}
