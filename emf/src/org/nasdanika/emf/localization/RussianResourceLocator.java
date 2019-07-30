package org.nasdanika.emf.localization;

import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.emf.EModelElementAnnotationResourceLocator;

/**
 * Resource locator which adds ``_ru`` suffix and falls back to UI.RU localization. 
 * @author Pavel
 *
 */
public class RussianResourceLocator extends EModelElementAnnotationResourceLocator {
	
	public RussianResourceLocator(EModelElement modelElement) {
		super(modelElement, "urn:org.nasdanika", key -> key+"_ru", UI.RU);
	}
	
}
