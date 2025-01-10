package org.nasdanika.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Service/capability interface for classes converting a specific documentation format to EObject
 */
public interface DocumentationFactory {
	
	/**
	 * @param Content type. E.g. text/markdown, text/html, text/plain
	 * @return
	 */
	boolean canHandle(java.lang.String contentType);
	
	/**
	 * 
	 * @param uri
	 * @return True if can handle a given URI. This method uses extensions and delegates to canHandle(format).
	 */
	default boolean canHandle(URI uri) {
		java.lang.String lastSegment = uri.lastSegment();
		if (Util.isBlank(lastSegment)) {
			return false;
		}
		
		if (lastSegment.endsWith(".md")) {
			return canHandle("markdown");
		}
		
		return canHandle(URLConnection.guessContentTypeFromName(lastSegment));
	}
	
	Collection<EObject> createDocumentation(
			Object context, 
			java.lang.String doc, 
			URI baseUri, 
			ProgressMonitor progressMonitor);
	
	default Collection<EObject> createDocumentation(
			Object context, 
			URI docRef, 
			ProgressMonitor progressMonitor) {
		
		try (InputStream in = DefaultConverter.INSTANCE.toInputStream(docRef)) {
			String doc = DefaultConverter.INSTANCE.toString(in);
			return createDocumentation(context, doc, docRef, progressMonitor);
		} catch (IOException e) {
			throw new NasdanikaException("Error loading documentation from '" + docRef + "': " + e, e);
		}
		
	};	

}
