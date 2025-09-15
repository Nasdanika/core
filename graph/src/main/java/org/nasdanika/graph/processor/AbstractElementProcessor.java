package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.nasdanika.graph.Element;

/**
 * Element processor wiring parent/child enpoints. 
 * @param <T> processor element type
 * @param <H> handler type for parent/child and connections.
 */
public abstract class AbstractElementProcessor<T extends Element,H,E> {
		
	protected T element;
	
	@ProcessorElement	
	public void setElement(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return element;
	}	
	
	protected E parentEndpoint;
	
	protected final E getParentEndpoint() {
		return parentEndpoint;
	}
	
	protected Map<Element,E> childEndpoints = new ConcurrentHashMap<>();
	
	/**
	 * Handler to be invoked by a given child
	 * @param child
	 * @param parentEndpoint
	 * @param childEndpoints
	 * @return
	 */
	protected abstract E getChildEndpoint(Element child, Supplier<E> parentEndpointSupplier, Map<Element,E> childEndpoints);
	
	@ChildProcessor
	public void addChildProcessor(AbstractElementProcessor<Element,H,E> childProcessor) {
		E callbackEndpoint = getChildEndpoint(childProcessor.getElement(), this::getParentEndpoint, childEndpoints); // Callback endpoint for the child
		E childEndpoint = childProcessor.getParentEndpoint(callbackEndpoint, childEndpoints);
		if (childEndpoint != null) {
			childEndpoints.put(childProcessor.getElement(), childEndpoint);
		}		
	}
	
	/**
	 * Endpoint to be used by the parent
	 * @return
	 */
	protected abstract E getParentEndpoint(E parentEndpoint, Map<Element,E> childEndpoints);
	
	/**
	 * Handler to be invoked by clients.
	 * @return
	 */
	public final H getHandler() {
		return getHandler(parentEndpoint, childEndpoints);
	}
	
	/**
	 * @param parentHandler Handler to work with the parent
	 * @param childHandlers Handlers to work with children
	 * @return
	 */
	protected abstract H getHandler(E parentEndpoint, Map<Element,E> childEndpoints);
	
}
