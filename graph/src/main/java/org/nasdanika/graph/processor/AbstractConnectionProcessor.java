package org.nasdanika.graph.processor;

import java.util.function.Function;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Connection passes invocations between its source and target
 * @param <T> Element type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public abstract class AbstractConnectionProcessor<T extends Connection,H,E> extends AbstractElementProcessor<T,H> {
	
	@TargetEndpoint
	public E targetEndpoint;
	
	@SourceEndpoint
	public E sourceEndpoint;
		
	@SourceHandler
	public final H getSourceHandler() {
		return getSourceHandler(
				parentProcessor == null ? null : parentProcessor.getChildHandler(getElement()),
				this::createChildHandler,
				targetEndpoint);
	};
	
	protected abstract H getSourceHandler(H parentHandler, Function<Element,H> childHandlerProvider, E targetEndpiont);	
	
	@TargetHandler
	public final H getTargetHandler() {
		return getTargetHandler(
				parentProcessor == null ? null : parentProcessor.getChildHandler(getElement()),
				this::createChildHandler,
				sourceEndpoint);
	}
	
	protected abstract H getTargetHandler(H parentHandler, Function<Element,H> childHandlerProvider, E sourceEndpiont);	
	
	@Override
	protected final H getHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
		return getHandler(parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
	}
	
	protected abstract H getHandler(H parentHandler, Function<Element,H> childHandlerProvider, E sourceEndpoint, E targetEndpoint);	
	
	@Override
	protected final H getChildHandler(Element child, H parentHandler, Function<Element,H> childHandlerProvider) {
		return getChildHandler(child, parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
	}
	
	protected abstract H getChildHandler(Element child, H parentHandler, Function<Element,H> childHandlerProvider, E sourceEndpoint, E targetEndpoint);	
	
	@Override
	protected final H getParentHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
		return getParentHandler(parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
	}
	
	protected abstract H getParentHandler(H parentHandler, Function<Element,H> childHandlerProvider, E sourceEndpoint, E targetEndpoint);

}
