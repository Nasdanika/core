package org.nasdanika.graph.processor.message;

import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

public interface MessageHandler<M extends Message> {
	
	/**
	 * Sends a message
	 * @param message
	 * @param progressMonitor
	 * @return A {@link Flux} of all messages produced as a result of sending a message, not including the message.. 
	 */
	Flux<M> process(M message, ProgressMonitor progressMonitor);

}
