package org.nasdanika.drawio.message.number;

import org.nasdanika.common.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.OutgoingMessage;

/**
 * A message sent to {@link Connection} from its source
 */
public class DoubleOutgoingMessage extends OutgoingMessage<Double> {

	public DoubleOutgoingMessage(Message<Node,Double> parent, Connection target, Double value) {
		super(parent, target, value);
	}

}
