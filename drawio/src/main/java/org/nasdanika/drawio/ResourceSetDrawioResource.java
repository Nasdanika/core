package org.nasdanika.drawio;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Loads EObjects Drawio diagram resources using {@link ObjectLoaderDrawioEObjectFactory}. 
 * @author Pavel
 *
 */
public abstract class ResourceSetDrawioResource<T extends EObject> extends DrawioResource<T> {

	public ResourceSetDrawioResource(URI uri) {
		super(uri);
	}
	
	/**
	 * @return {@link ResourceSet} to load objects.
	 */
	protected abstract ResourceSet getLoadingResourceSet();
	
	private ResourceSetDrawioEObjectFactory<T> processorFactory = new ResourceSetDrawioEObjectFactory<T>() {

		@Override
		protected ResourceSet getResourceSet() {
			return ResourceSetDrawioResource.this.getLoadingResourceSet();
		}
		
		@Override
		protected URI getBaseURI() {
			return ResourceSetDrawioResource.this.getURI();
		}

		protected java.util.List<String> getPropertyPrefixes() {
			return ResourceSetDrawioResource.this.getPropertyPrefixes();
		};
		
		
		@Override
		protected T createSemanticElement(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
			T semanticElement = super.createSemanticElement(config, progressMonitor);
			if (semanticElement != null) {
				configureSemanticElement(config, semanticElement, progressMonitor);
			}
			return semanticElement;
		}		
				
	};
	
	protected abstract java.util.List<String> getPropertyPrefixes();
	
	/**
	 * Sets a parent for the resource roots. Delegates to the processor factory to use graph data (diagram element properties) to establish parent/child relationships.
	 * This method is used when a parent object has an attribute containing diagram URI. In this case a diagram is loaded and then this method is called to 
	 * link diagram's root semantic objects to the parent object. 
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	public void setParent(T parent) {
		for (EObject root: new ArrayList<>(getContents())) {
			processorFactory.setParent((T) root, parent);
		}
	}

	@Override
	protected ProcessorFactory<T, ?, ?> getProcessorFactory() {
		return processorFactory;
	}
	
	@Override
	protected Stream<? extends EObject> getSemanticElements(Map<Element, ProcessorInfo<T>> registry) {
		return registry.values().stream().map(pi -> pi.getProcessor()).filter(Objects::nonNull);
	}
	
	protected abstract void configureSemanticElement(ProcessorConfig<T> config, T semanticElement, ProgressMonitor progressMonitor);	

}