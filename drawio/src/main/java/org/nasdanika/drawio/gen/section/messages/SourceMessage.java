package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.gen.section.NodeProcessor;

/**
 * A message sent from a {@link Connection} to an source {@link Node}.
 */
public class SourceMessage extends Message<NodeProcessor> {

	public SourceMessage(Message<?> parent, NodeProcessor processor) {
		super(parent, processor);
	}

}
