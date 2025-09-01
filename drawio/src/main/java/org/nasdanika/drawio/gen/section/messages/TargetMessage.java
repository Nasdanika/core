package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.gen.section.NodeProcessor;

/**
 * A message sent from a {@link Connection} to its target {@link Node}.
 */
public class TargetMessage extends Message<NodeProcessor> {

	public TargetMessage(Message<?> parent, NodeProcessor processor) {
		super(parent, processor);
	}

}
