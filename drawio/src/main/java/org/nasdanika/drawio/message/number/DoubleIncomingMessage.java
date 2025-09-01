package org.nasdanika.drawio.message.number;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.IncomingMessage;

/**
 * A message sent to {@link Connection} from its target
 */
public class DoubleIncomingMessage extends IncomingMessage<Double> {

	public DoubleIncomingMessage(Message<Node,Double> parent, Connection target, Double value) {
		super(parent, target, value);
	}

}
