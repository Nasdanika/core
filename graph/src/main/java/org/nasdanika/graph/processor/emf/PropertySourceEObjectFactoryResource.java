package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ProcessorConfig;

public abstract class PropertySourceEObjectFactoryResource<T extends EObject> extends AbstractEObjectFactoryProcessorResource<T> {

	protected PropertySourceEObjectFactoryResource(URI uri) {
		super(uri);
	}
		
	protected abstract java.util.List<String> getPropertyPrefixes();
	
	/**
	 * Context for spec interpolation.
	 * @return
	 */
	protected abstract Context getContext();	

	@Override
	protected abstract PropertySourceEObjectFactory<T> getProcessorFactory();
	
	protected abstract T configureSemanticElement(ProcessorConfig<T> config, T semanticElement, ProgressMonitor progressMonitor);	

}
