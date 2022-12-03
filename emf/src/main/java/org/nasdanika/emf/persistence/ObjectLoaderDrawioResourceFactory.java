package org.nasdanika.emf.persistence;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Creates {@link YamlResource}
 * @author Pavel
 *
 */
public class ObjectLoaderDrawioResourceFactory<T extends EObject> extends ResourceFactoryImpl {
	
	private ProgressMonitor progressMonitor; TODO - get for a resource
	private ObjectLoader loader; TODO - get for a resource
	private Context context;

	public ObjectLoaderDrawioResourceFactory(ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		this.loader = loader;
		this.context = context;
		this.progressMonitor = progressMonitor;
	}

	@Override
	public Resource createResource(URI uri) {
		return new ObjectLoaderDrawioResource<T>(uri) {
			
			@Override
			protected ObjectLoader getLoader() {
				return loader;
			}

			@Override
			protected Context getContext() {
				return resourceContext(uri);
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor() {
				return progressMonitor;
			}

			@Override
			protected void configureSemanticElement(
					ProcessorConfig<T> config, 
					T semanticElement,
					ProgressMonitor progressMonitor) {
				
				ObjectLoaderDrawioResourceFactory.this.configureSemanticElement(config, semanticElement, this, progressMonitor);
			}

			@Override
			protected List<String> getPropertyPrefixes() {
				return ObjectLoaderDrawioResourceFactory.this.getPropertyPrefixes(this);
			}
			
		};
	}

	/**
	 * Override to implement property namespacing or profiles.
	 * @param resource
	 * @return Property name prefixes. This implementation returns a singleton of an empty string.
	 */
	protected java.util.List<String> getPropertyPrefixes(ObjectLoaderDrawioResource<T> resource) {
		return Collections.singletonList("");
	};
	
	/**
	 * Override to customize resource context, e.g. add uri-based properties or services.
	 * This imple
	 * @param uri
	 * @return
	 */
	protected Context resourceContext(URI uri) {
		MutableContext ret = context.fork();

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
	 * Override to customize semantic elements. For example, set semantic element name from a diagram element label.
	 * @param config
	 * @param semanticElement
	 * @param resource
	 * @param progressMonitor
	 */
	protected void configureSemanticElement(ProcessorConfig<T> config, T semanticElement, Resource resource, ProgressMonitor progressMonitor) {	}		
	
}
