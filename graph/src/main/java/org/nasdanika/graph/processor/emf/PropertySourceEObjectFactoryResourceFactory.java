package org.nasdanika.graph.processor.emf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
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

/**
 * Loads {@link ResourceSetDrawioResource}'s 
 * @author Pavel
 *
 */
public abstract class PropertySourceEObjectFactoryResourceFactory<T extends EObject> extends ResourceFactoryImpl {
			
	@Override
	public abstract PropertySourceEObjectFactoryResource<T> createResource(URI uri);
	
	/**
	 * Context for spec interpolation.
	 * @param uri
	 * @return
	 */
	protected Context getContext(PropertySourceEObjectFactoryResource<T> resource) {
		MutableContext ret = Context.EMPTY_CONTEXT.fork();

		Function<String, URL> resolver = path -> {
			try {
				return new URL(new URL(resource.getURI().toString()), path);
			} catch (MalformedURLException e) {
				throw new NasdanikaException("Cannot resolve '" + path + "' relative to " + resource.getURI() + ": " + e, e);
			}
		};
		
		ret.put("embedded-image", Util.createEmbeddedImagePropertyComputer(resolver));
		ret.put("embedded-image-data", Util.createEmbeddedImageDataPropertyComputer(resolver));
		ret.put("include", Util.createIncludePropertyComputer(resolver));
		ret.put("include-markdown", Util.createIncludeMarkdownPropertyComputer(resolver));
		
		return ret;
	}

	/**
	 * @param uri
	 * @return progress monitor for loading a resource identified by the uri
	 */
	protected abstract ProgressMonitor getProgressMonitor(PropertySourceEObjectFactoryResource<T> resource);

	/**
	 * Override to implement property namespacing or profiles.
	 * @param resource
	 * @return Property name prefixes. This implementation returns a singleton of an empty string.
	 */
	protected java.util.List<String> getPropertyPrefixes(PropertySourceEObjectFactoryResource<T> resource) {
		return Collections.singletonList("");
	};
	
	/**
	 * Override to customize semantic elements. For example, set semantic element name from a diagram element label.
	 * This method can replace the semantic element. For example, if the semantic element is null this method can create an element.
	 * @param config
	 * @param semanticElement
	 * @param resource
	 * @param progressMonitor
	 */
	protected T configureSemanticElement(ProcessorConfig<T> config, T semanticElement, Resource resource, ProgressMonitor progressMonitor) {
		return semanticElement;
	}		
	
}
