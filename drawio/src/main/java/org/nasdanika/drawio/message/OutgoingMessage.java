package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent to {@link Connection} from its source
 */
public class OutgoingMessage<V> extends Message<Connection,V> {

	public OutgoingMessage(Message<Node,V> parent, Connection target, V value) {
		super(parent, target, value);
	}

}
