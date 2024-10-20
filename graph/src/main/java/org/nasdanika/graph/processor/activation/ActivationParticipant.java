package org.nasdanika.graph.processor.activation;

import java.io.OutputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Participates in an activation
 */
public interface ActivationParticipant {
	
	/**
	 * Endpoint is used by the client code to provide/build activator handler input. 
	 * E.g. it might be a {@link Consumer} or an {@link OutputStream} or some collection type, or literally anything with a state.
	 * Or it may be a {@link Supplier} of the above objects. 
	 * Calling endpoint methods outside of activation shall throw IllegalStateException. 
	 * @return
	 */
	Object getEndpoint();
	
	/**
	 * Enrolls this participant into an activation. 
	 * Enrollment stores state changes made via the endpoint.
	 * @param activation
	 * @return
	 */
	Enrollment enroll(Activation activation);

}
