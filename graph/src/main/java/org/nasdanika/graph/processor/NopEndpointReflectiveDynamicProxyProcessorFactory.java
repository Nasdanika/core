package org.nasdanika.graph.processor;

public abstract class NopEndpointReflectiveDynamicProxyProcessorFactory<P, H> extends ReflectiveProcessorFactory<P, H, H> implements NopEndpointProcessorFactory<P, H>, DynamicProxyProcessorFactory<P, H, H> {

	public NopEndpointReflectiveDynamicProxyProcessorFactory(Object target, IntrospectionLevel introspectionLevel) {
		super(target, introspectionLevel);
	}

}
