package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.function.Supplier;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Connection passes invocations between its source and target
 * @param <T> Element type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public abstract class AbstractConnectionProcessor<T extends Connection,H,E> extends AbstractElementProcessor<T,H,E> {
	
	@TargetEndpoint
	public E targetEndpoint;
	
	public E getTargetEndpoint() {
		return targetEndpoint;
	}
	
	@SourceEndpoint
	public E sourceEndpoint;
	
	public E getSourceEndpoint() {
		return sourceEndpoint;
	}
		
	@SourceHandler
	public final H getSourceHandler() {
		return getSourceHandler(
				this::getParentEndpoint,
				childEndpoints,
				this::getSourceEndpoint,
				this::getTargetEndpoint);
	};
	
	protected abstract H getSourceHandler(
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints, 
			Supplier<E> sourceEndpiontSupplier, 
			Supplier<E> targetEndpiontSupplier);	
	
	@TargetHandler
	public final H getTargetHandler() {
		return getTargetHandler(
				this::getParentEndpoint,
				childEndpoints,
				this::getSourceEndpoint,
				this::getTargetEndpoint);
	}
	
	protected abstract H getTargetHandler(
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints,
			Supplier<E> sourceEndpiontSupplier, 
			Supplier<E> targetEndpiontSupplier);
	
	@Override
	protected final H getHandler(E parentEndpoint, Map<Element, E> childEndpoints) {
		return getHandler(parentEndpoint, childEndpoints, this::getSourceEndpoint, this::getTargetEndpoint);
	}
	
	protected abstract H getHandler(
			E parentEndpoint, 
			Map<Element,E> childEndpoints, 
			Supplier<E> sourceEndpointSupplier, 
			Supplier<E> targetEndpointSupplier);	
	
	@Override
	protected final E getChildEndpoint(Element child, Supplier<E> parentEndpointSupplier, Map<Element, E> childEndpoints) {
		return getChildEndpoint(child, parentEndpointSupplier, childEndpoints, this::getSourceEndpoint, this::getTargetEndpoint);
	}
	
	protected abstract E getChildEndpoint(
			Element child, 
			Supplier<E> parentEndpointSupplier, 
			Map<Element,E> childEndpoints, 
			Supplier<E> sourceEndpointSupplier, 
			Supplier<E> targetEndpointSupplier);	

	@Override
	protected final E getParentEndpoint(E parentEndpoint, Map<Element, E> childEndpoints) {
		return getParentEndpoint(parentEndpoint, childEndpoints, this::getSourceEndpoint, this::getTargetEndpoint);
	}
	
	protected abstract E getParentEndpoint(
			E parentEndpoint, 
			Map<Element, E> childEndpoints, 
			Supplier<E> sourceEndpointSupplier, 
			Supplier<E> targetEndpointSupplier);

}
