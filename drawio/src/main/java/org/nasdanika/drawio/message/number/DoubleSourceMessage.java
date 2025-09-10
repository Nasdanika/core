package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.NodeProcessor;
import org.nasdanika.graph.message.SourceMessage;

/**
 * A message sent from a {@link Connection} to the source {@link Node}.
 */
public class DoubleSourceMessage extends SourceMessage<Double> {

	public DoubleSourceMessage(DoubleIncomingConnectionMessage parent, Node target, Double value, NodeProcessor<Double> processor) {
		super(parent, target, value, processor);
	}

}
