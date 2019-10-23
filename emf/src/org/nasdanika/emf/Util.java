package org.nasdanika.emf;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;

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
	

}
