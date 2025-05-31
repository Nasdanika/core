package org.nasdanika.graph.processor.message;

import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

public interface MessageHandler<M extends Message> {
	
	/**
	 * Processes a message
	 * @param message
	 * @param progressMonitor
	 * @return A {@link Flux} of all messages produced as a result of processing the argument message, not including the message itself. 
	 */
	Flux<M> process(M message, ProgressMonitor progressMonitor);

}
