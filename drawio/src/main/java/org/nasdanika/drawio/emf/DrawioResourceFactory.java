package org.nasdanika.drawio.emf;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.emf.ResourceSetPropertySourceEObjectFactoryResourceFactory;

/**
 * Loads {@link ResourceSetDrawioResource}'s 
 * @author Pavel
 *
 */
public abstract class DrawioResourceFactory<T extends EObject> extends ResourceSetPropertySourceEObjectFactoryResourceFactory<T> {
			
	protected abstract ResourceSet getResourceSet();

	@Override
	public DrawioResource<T> createResource(URI uri) {		
		return new DrawioResource<T>(uri) {
			
			@Override
			protected ProgressMonitor getProgressMonitor() {
				return DrawioResourceFactory.this.getProgressMonitor(this);
			}

			@Override
			protected List<String> getPropertyPrefixes() {
				return DrawioResourceFactory.this.getPropertyPrefixes(this);
			}

			@Override
			protected ResourceSet getLoadingResourceSet() {
				return DrawioResourceFactory.this.getResourceSet();
			}

			@Override
			protected T configureSemanticElement(
					ProcessorConfig<T> config, 
					T semanticElement,
					ProgressMonitor progressMonitor) {
				
				return DrawioResourceFactory.this.configureSemanticElement(config, semanticElement, this, progressMonitor);
			}

			@Override
			protected Context getContext() {
				return DrawioResourceFactory.this.getContext(this);
			}
			
		};
	}
	
}
