package org.nasdanika.drawio.message.number;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.IncomingConnectionMessage;

/**
 * A message sent to {@link Connection} from its target
 */
public class DoubleIncomingConnectionMessage extends IncomingConnectionMessage<Double> {

	public DoubleIncomingConnectionMessage(Message<Node,Double> parent, Connection target, Double value) {
		super(parent, target, value);
	}

}
