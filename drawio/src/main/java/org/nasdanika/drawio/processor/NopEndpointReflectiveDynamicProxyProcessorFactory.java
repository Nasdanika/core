package org.nasdanika.drawio.processor;

public class NopEndpointReflectiveProcessorFactory<P, T, R> extends ReflectiveProcessorFactory<P, T, R, T, R> implements NopEndpointProcessorFactory<P, T, R> {

	public NopEndpointReflectiveProcessorFactory(Object target, IntrospectionLevel introspectionLevel) {
		super(target, introspectionLevel);
	}

}
