package org.nasdanika.drawio;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ProcessorConfig;

/**
 * Loads {@link ResourceSetDrawioResource}'s 
 * @author Pavel
 *
 */
public abstract class ResourceSetDrawioResourceFactory<T extends EObject> extends ResourceFactoryImpl {
			
	protected abstract ResourceSet getResourceSet();
	
	/**
	 * @param uri
	 * @return progress monitor for loading a resource identified by the uri
	 */
	protected abstract ProgressMonitor getProgressMonitor(URI uri);

	@Override
	public Resource createResource(URI uri) {
		return new ResourceSetDrawioResource<T>(uri) {
			
			@Override
			protected ProgressMonitor getProgressMonitor() {
				return ResourceSetDrawioResourceFactory.this.getProgressMonitor(uri);
			}

			@Override
			protected List<String> getPropertyPrefixes() {
				return ResourceSetDrawioResourceFactory.this.getPropertyPrefixes(this);
			}

			@Override
			protected ResourceSet getLoadingResourceSet() {
				return ResourceSetDrawioResourceFactory.this.getResourceSet();
			}

			@Override
			protected void configureSemanticElement(
					ProcessorConfig<T> config, 
					T semanticElement,
					ProgressMonitor progressMonitor) {
				
				ResourceSetDrawioResourceFactory.this.configureSemanticElement(config, semanticElement, this, progressMonitor);
			}
			
		};
	}

	/**
	 * Override to implement property namespacing or profiles.
	 * @param resource
	 * @return Property name prefixes. This implementation returns a singleton of an empty string.
	 */
	protected java.util.List<String> getPropertyPrefixes(ResourceSetDrawioResource<T> resource) {
		return Collections.singletonList("");
	};
	
	/**
	 * Override to customize semantic elements. For example, set semantic element name from a diagram element label.
	 * @param config
	 * @param semanticElement
	 * @param resource
	 * @param progressMonitor
	 */
	protected void configureSemanticElement(ProcessorConfig<T> config, T semanticElement, Resource resource, ProgressMonitor progressMonitor) {	}		
	
}
