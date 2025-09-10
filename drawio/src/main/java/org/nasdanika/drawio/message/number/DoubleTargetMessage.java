package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.NodeProcessor;
import org.nasdanika.graph.message.TargetMessage;

/**
 * A message sent from a {@link Connection} to its target {@link Node}.
 */
public class DoubleTargetMessage extends TargetMessage<Double> {

	public DoubleTargetMessage(DoubleOutgoingConnectionMessage parent, Node target, Double value, NodeProcessor<Double> processor) {
		super(parent, target, value, processor);
	}

}
