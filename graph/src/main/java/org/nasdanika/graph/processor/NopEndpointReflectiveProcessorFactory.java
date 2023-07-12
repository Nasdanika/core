package org.nasdanika.graph.processor;

/**
 * Provides default implementations of createEndpoint() and createHandlerProxy() methods.
 * @author Pavel
 *
 * @param <P>
 * @param <H>
 */
public abstract class NopEndpointReflectiveProcessorFactory<P, H, R> extends ReflectiveProcessorFactory<P, H, H, R> implements NopEndpointProcessorConfigFactory<P, H, R> {

	public NopEndpointReflectiveProcessorFactory(Object... targets) {
		super(targets);
	}

}
