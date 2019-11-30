package org.nasdanika.emf;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;

public class Util {

	private static final String NASDANIKA_ANNOTATION_SOURCE = "urn:org.nasdanika";

	private Util() {
		// Singleton
	}
		
	public static EAnnotation getNasdanikaAnnotation(EModelElement modelElement) {
		return modelElement == null ? null : modelElement.getEAnnotation(NASDANIKA_ANNOTATION_SOURCE);
	}
	
	public static String getCategory(EModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		EAnnotation na = getNasdanikaAnnotation(modelElement);
		return na == null ? null : na.getDetails().get("category");		
	}
	
	public static final String BUNDLE_PROTOCOL = "bundle://";
	
	/**
	 * Resolves reference relative to the resource. <code>bundle://</code> protocol can be used to reference resources in OSGi bundles similarly to <code>plugin://</code> protocol.  
	 * @param resource Used to resolve relative references if not null.
	 * @param reference Reference.
	 * @return
	 * @throws Exception
	 */
	public static URL resolveReference(Resource resource, String reference) throws Exception {
		if (reference.startsWith(BUNDLE_PROTOCOL)) {
			String bp = reference.substring(BUNDLE_PROTOCOL.length());
			int slashIdx = bp.indexOf("/");
			Bundle bundle = Platform.getBundle(bp.substring(0, slashIdx));
			return bundle.getEntry(bp.substring(slashIdx));
		}
		
		if (resource != null) {
			URI resUri = resource.getURI();
			URI refUri = URI.createURI(reference);
			URI resolvedUri = refUri.resolve(resUri);
			return new URL(resolvedUri.toString());			
		}
		
		return new URL(reference);
	}
	
	

}
