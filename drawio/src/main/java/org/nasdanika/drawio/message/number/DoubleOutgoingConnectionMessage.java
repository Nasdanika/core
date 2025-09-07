package org.nasdanika.drawio.message.number;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.OutgoingConnectionMessage;

/**
 * A message sent to {@link Connection} from its source
 */
public class DoubleOutgoingConnectionMessage extends OutgoingConnectionMessage<Double> {

	public DoubleOutgoingConnectionMessage(Message<Node,Double> parent, Connection target, Double value) {
		super(parent, target, value);
	}

}
