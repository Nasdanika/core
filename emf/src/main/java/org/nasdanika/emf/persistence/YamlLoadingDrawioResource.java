package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.DrawioResource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads EObjects Drawio diagram resources using {@link YamlLoadingDrawioEObjectFactory}. 
 * @author Pavel
 *
 */
public abstract class YamlLoadingDrawioResource<T extends EObject> extends DrawioResource<T> {

	public YamlLoadingDrawioResource(URI uri) {
		super(uri);
	}
	
	private YamlLoadingDrawioEObjectFactory<T> processorFactory = new YamlLoadingDrawioEObjectFactory<T>() {

		@Override
		protected ObjectLoader getLoader() {
			return YamlLoadingDrawioResource.this.getLoader();
		}
		
		@Override
		protected Context getContext() {
			return YamlLoadingDrawioResource.this.getContext();
		}
		
		@Override
		protected URI getBaseURI() {
			return YamlLoadingDrawioResource.this.getURI();
		}
		
		@Override
		protected T createSemanticElement(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
			T semanticElement = super.createSemanticElement(config, progressMonitor);
			if (semanticElement != null) {
				configureSemanticElement(config, semanticElement, progressMonitor);
			}
			return semanticElement;
		}
		
	};	
	
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
	
	protected abstract ObjectLoader getLoader();	

	protected abstract Context getContext();
	
	protected abstract void configureSemanticElement(ProcessorConfig<T> config, T semanticElement, ProgressMonitor progressMonitor);	
	
	@Override
	protected Stream<? extends EObject> getSemanticElements(Map<Element, ProcessorInfo<T>> registry) {
		return registry.values().stream().map(pi -> pi.getProcessor()).filter(Objects::nonNull);
	}

}