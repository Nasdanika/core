package org.nasdanika.drawio.gen.section.messages;

import org.nasdanika.drawio.gen.section.ConnectionProcessor;

/**
 * A message sent to {@link ConnectionProcessor} from its source
 */
public class IncomingMessage extends Message<ConnectionProcessor> {

	public IncomingMessage(Message<?> parent, ConnectionProcessor processor) {
		super(parent, processor);
	}

}
