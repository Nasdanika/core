package org.nasdanika.emf;

import java.util.Locale;

import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.Context;

/**
 * Resource locator which adds ``_<default locale language>`` suffix and falls back to the chain passed to the constructor. 
 * @author Pavel
 *
 */
public class LocaleLanguageResourceLocator extends EModelElementAnnotationResourceLocator {
	
	public LocaleLanguageResourceLocator(EModelElement modelElement, Locale locale, Context chain) {		
		super(modelElement, "urn:org.nasdanika", key -> key+"_"+locale.getLanguage(), chain);
	}
	
}
