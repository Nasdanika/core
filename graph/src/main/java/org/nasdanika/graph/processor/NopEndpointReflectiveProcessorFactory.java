package org.nasdanika.graph.processor;

/**
 * Provides default implementations of createEndpoint() and createHandlerProxy() methods.
 * @author Pavel
 *
 * @param <P>
 * @param <H>
 */
public abstract class NopEndpointReflectiveProcessorFactory<P, H, R> extends ReflectiveProcessorFactory<P, H, H, R> implements NopEndpointProcessorFactory<P, H, R> {

	public NopEndpointReflectiveProcessorFactory(IntrospectionLevel introspectionLevel, Object... targets) {
		super(introspectionLevel, targets);
	}

}
