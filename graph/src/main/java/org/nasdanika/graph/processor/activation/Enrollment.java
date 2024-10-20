package org.nasdanika.graph.processor.activation;

import java.util.function.Consumer;

/**
 * Enrollment of an {@link ActivationParticipant} in an {@link Activation}
 */
public interface Enrollment {
	
	/**
	 * Submits tasks to be executed in the context of an activation
	 * @param taskConsumer
	 */
	void submit(Consumer<Runnable> taskConsumer);
	
	void error(Exception ex);
	
	/**
	 * Clears enrollment state
	 */
	void close();

}
