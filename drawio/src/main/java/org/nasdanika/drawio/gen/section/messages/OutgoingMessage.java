package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.gen.section.ConnectionProcessor;

/**
 * A message sent to {@link ConnectionProcessor} from its target
 */
public class OutgoingMessage extends Message<ConnectionProcessor> {

	public OutgoingMessage(Message<?> parent, ConnectionProcessor processor) {
		super(parent, processor);
	}

}
