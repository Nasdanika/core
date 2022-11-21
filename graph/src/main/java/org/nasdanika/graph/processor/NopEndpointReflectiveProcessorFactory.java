package org.nasdanika.graph.processor;

/**
 * Provides default implementations of createEndpoint() and createHandlerProxy() methods.
 * @author Pavel
 *
 * @param <P>
 * @param <H>
 */
public abstract class NopEndpointReflectiveProcessorFactory<P, H> extends ReflectiveProcessorFactory<P, H, H> implements NopEndpointProcessorFactory<P, H> {

	public NopEndpointReflectiveProcessorFactory(IntrospectionLevel introspectionLevel, Object... targets) {
		super(introspectionLevel, targets);
	}

}
