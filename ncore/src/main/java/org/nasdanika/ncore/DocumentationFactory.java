package org.nasdanika.ncore;

import java.net.URLConnection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Service/capability interface for classes converting a specific documentation format to EObject to add to {@link Documented}
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
	
	EObject createDocumentation(java.lang.String doc, URI baseUri, ProgressMonitor progressMonitor);
	
	EObject createDocumentation(URI docRef, ProgressMonitor progressMonitor);	

}
