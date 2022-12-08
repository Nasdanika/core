package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Mix-in interface for resources to set a parent for resource roots. 
 * @author Pavel
 *
 * @param <T>
 */
public interface AbstractEObjectFactoryProcessorResource<T extends EObject> extends Resource {
		
	/**
	 * Sets a parent for the resource roots. Delegates to the processor factory to use graph data (diagram element properties) to establish parent/child relationships.
	 * This method is used when a parent object has an attribute containing diagram URI. In this case a diagram is loaded and then this method is called to 
	 * link diagram's root semantic objects to the parent object. 
	 * @param parent
	 */
	void setParent(T parent);

// Method implementation: 	
//		AbstractEObjectFactory<T> processorFactory = getProcessorFactory();
//		for (EObject root: new ArrayList<>(getContents())) {
//			processorFactory.setParent((T) root, parent);
//		}

}
