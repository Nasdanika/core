package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.function.Function;

import org.nasdanika.graph.Element;

/**
 * Element processor wiring parent/child enpoints. 
 * @param <T> processor element type
 * @param <H> handler type for parent/child and connections.
 */
public abstract class AbstractElementProcessor<T extends Element,H> {
	
	@ParentProcessor
	public AbstractElementProcessor<Element,H> parentProcessor;
		
	protected T element;
	
	@ProcessorElement	
	public void setElement(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return element;
	}
	
	@Registry 
	public Map<T, ProcessorInfo<AbstractElementProcessor<Element,H>>> registry;
	
	@ChildProcessors
	public Map<T, ProcessorInfo<AbstractElementProcessor<Element,H>>> children;
	
	/**
	 * Handler to be invoked by clients.
	 * @return
	 */
	public final H getHandler() {
		H parentHandler = parentProcessor == null ? null : parentProcessor.getChildHandler(getElement());
		return getHandler(parentHandler, this::createChildHandler);
	}
	
	/**
	 * @param parentHandler Handler to work with the parent
	 * @param childHandlers Handlers to work with children
	 * @return
	 */
	protected abstract H getHandler(H parentHandler, Function<Element,H> childHandlerProvider);
	
	/**
	 * Handler to be invoked by the parent.
	 * @param parentHandler handler provided by the parent for callbacks.
	 * @return
	 */
	protected final H getParentHandler(H parentHandler) {
		return getParentHandler(parentHandler,this::createChildHandler);
	}
	
	/**
	 * @param childHandlers Handlers to work with children
	 * @return
	 */
	protected abstract H getParentHandler(H parentHandler, Function<Element,H> childHandlerProvider);
	
	/**
	 * Handler to be invoked by a given child.
	 * @return
	 */
	protected final H getChildHandler(Element child) {
		return getChildHandler(
				child,
				parentProcessor == null ? null : parentProcessor.getChildHandler(getElement()),
				this::createChildHandler);
	}
	
	/**
	 * @param parentHandler Handler to work with the parent
	 * @param childHandlers Handlers to work with children
	 * @return
	 */
	protected abstract H getChildHandler(Element child, H parentHandler, Function<Element,H> childHandlerProvider);
	
	/**
	 * Obtains a handler from a child providing own callback handler to the child
	 * @param child
	 * @return
	 */
	protected H createChildHandler(Element child) {
		if (children == null) {
			return null;
		}
		ProcessorInfo<AbstractElementProcessor<Element, H>> cpi = children.get(child);
		if (cpi == null) {
			return null;
		}
		AbstractElementProcessor<Element, H> cp = cpi.getProcessor();
		if (cp == null) {
			return null;
		}
		return cp.getParentHandler(getChildHandler(child));
	}
	
}
