package org.nasdanika.graph.processor.emf;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public abstract class AbstractEObjectFactoryProcessorResource<T extends EObject> extends GraphProcessorResource<T, T> {
	
	protected AbstractEObjectFactoryProcessorResource(URI uri) {
		super(uri);
	}

	@Override
	protected abstract AbstractEObjectFactory<T> getProcessorFactory();
	
	@Override
	protected T getSemanticElement(T processor) {
		return processor;
	}
		
	/**
	 * Sets a parent for the resource roots. Delegates to the processor factory to use graph data (diagram element properties) to establish parent/child relationships.
	 * This method is used when a parent object has an attribute containing diagram URI. In this case a diagram is loaded and then this method is called to 
	 * link diagram's root semantic objects to the parent object. 
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	public void setParent(T parent) {
		AbstractEObjectFactory<T> processorFactory = getProcessorFactory();
		for (EObject root: new ArrayList<>(getContents())) {
			processorFactory.setParent((T) root, parent);
		}
	}	

}
