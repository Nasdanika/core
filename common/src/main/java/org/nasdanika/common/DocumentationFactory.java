package org.nasdanika.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Service/capability interface for classes converting a specific documentation format to EObject
 */
public interface DocumentationFactory extends Composable<DocumentationFactory> {
	
	record Requirement(boolean inline) {}
	
	/**
	 * @param Content type. E.g. text/markdown, text/html, text/plain
	 * @return
	 */
	boolean canHandle(java.lang.String contentType);
	
	Collection<String> getExtensions();
	
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
		
		int dotIdx = lastSegment.lastIndexOf('.');
		if (dotIdx != -1) {
			if (getExtensions().contains(lastSegment.substring(dotIdx + 1))) {
				return true;
			}
		}
		
		return canHandle(URLConnection.guessContentTypeFromName(lastSegment));
	}
	
	/**
	 * 
	 * @param context
	 * @param doc
	 * @param contentType Content type for factories which support more than one
	 * @param baseUri
	 * @param progressMonitor
	 * @return
	 */
	Collection<EObject> createDocumentation(
			Object context, 
			java.lang.String doc, 
			java.lang.String contentType,
			URI baseUri, 
			ProgressMonitor progressMonitor);
	
	default Collection<EObject> createDocumentation(
			Object context, 
			URI docRef, 
			ProgressMonitor progressMonitor) {
		
		try (InputStream in = DefaultConverter.INSTANCE.toInputStream(docRef)) {
			String doc = DefaultConverter.INSTANCE.toString(in);
			return createDocumentation(context, doc, null, docRef, progressMonitor);
		} catch (IOException e) {
			throw new NasdanikaException("Error loading documentation from '" + docRef + "': " + e, e);
		}		
	}	
	
	@Override
	default DocumentationFactory compose(DocumentationFactory other) {		
		return new DocumentationFactory() {

			@Override
			public boolean canHandle(String contentType) {
				return DocumentationFactory.this.canHandle(contentType) || other.canHandle(contentType);
			}
			
			@Override
			public boolean canHandle(URI uri) {
				return DocumentationFactory.this.canHandle(uri) || other.canHandle(uri);
			}			
			
			@Override
			public Collection<String> getExtensions() {
				Collection<String> extensions = new ArrayList<>(DocumentationFactory.this.getExtensions());
				extensions.addAll(other.getExtensions());
				return extensions;
			}

			@Override
			public Collection<EObject> createDocumentation(
					Object context, 
					java.lang.String doc, 
					java.lang.String contentType, 
					URI baseUri,
					ProgressMonitor progressMonitor) {
				
				if (DocumentationFactory.this.canHandle(contentType)) {
					return DocumentationFactory.this.createDocumentation(context, doc, contentType, baseUri, progressMonitor);
				}
				
				return other.createDocumentation(context, doc, contentType, baseUri, progressMonitor);
			}
			
			public Collection<EObject> createDocumentation(
					Object context, 
					URI docRef, 
					ProgressMonitor progressMonitor) {
				
				if (DocumentationFactory.this.canHandle(docRef)) {
					return DocumentationFactory.this.createDocumentation(context, docRef, progressMonitor);
				}
				
				return other.createDocumentation(context, docRef, progressMonitor);
			}				
			
		};
	}

}
