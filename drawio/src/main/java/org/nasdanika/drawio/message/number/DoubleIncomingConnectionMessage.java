package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.message.ConnectionProcessor;
import org.nasdanika.drawio.message.ElementMessage;
import org.nasdanika.drawio.message.IncomingConnectionMessage;

/**
 * A message sent to {@link Connection} from its target
 */
public class DoubleIncomingConnectionMessage extends IncomingConnectionMessage<Double> {

	public DoubleIncomingConnectionMessage(ElementMessage<?,Double,?> parent, Connection target, Double value, ConnectionProcessor<Double> processor) {
		super(parent, target, value, processor);
	}

}
