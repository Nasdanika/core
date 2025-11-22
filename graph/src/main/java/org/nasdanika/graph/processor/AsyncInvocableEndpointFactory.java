package org.nasdanika.graph.processor;

import java.util.concurrent.Executor;

import org.nasdanika.common.AsyncInvocable;
import org.nasdanika.common.Invocable;
import org.nasdanika.graph.Element;

/**
 * If handler is instance of {@link AsyncInvocable} then it is invoked asynchronously using {@link Invocable}.async
 */
public class AsyncInvocableEndpointFactory implements EndpointFactory<Object, Object> {
	
	private Executor executor;

	public AsyncInvocableEndpointFactory(Executor executor) {
		this.executor = executor;
	}

	@Override
	public Object createEndpoint(Element element, Object handler, HandlerType type) {
		if (handler instanceof AsyncInvocable) {
			return ((Invocable) handler).async(executor);
		}
		return handler;
	}

}
