package org.nasdanika.drawio.message;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * A message sent to {@link Connection} from its target
 */
public class IncomingMessage<V> extends Message<Connection,V> {

	public IncomingMessage(Message<Node,V> parent, Connection target, V value) {
		super(parent, target, value);
	}

}
