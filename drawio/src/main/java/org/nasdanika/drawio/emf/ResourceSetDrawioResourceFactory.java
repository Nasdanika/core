package org.nasdanika.drawio.emf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
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

			@Override
			protected Context getContext() {
				return ResourceSetDrawioResourceFactory.this.getContext(getURI());
			}
			
		};
	}
	
	/**
	 * Context for spec interpolation.
	 * @param uri
	 * @return
	 */
	protected Context getContext(URI uri) {
		MutableContext ret = Context.EMPTY_CONTEXT.fork();

		Function<String, URL> resolver = path -> {
			try {
				return new URL(new URL(uri.toString()), path);
			} catch (MalformedURLException e) {
				throw new NasdanikaException("Cannot resolve '" + path + "' relative to " + uri + ": " + e, e);
			}
		};
		
		ret.put("embedded-image", Util.createEmbeddedImagePropertyComputer(resolver));
		ret.put("embedded-image-data", Util.createEmbeddedImageDataPropertyComputer(resolver));
		ret.put("include", Util.createIncludePropertyComputer(resolver));
		ret.put("include-markdown", Util.createIncludeMarkdownPropertyComputer(resolver));
		
		return ret;
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
