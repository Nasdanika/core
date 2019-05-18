package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;

/**
 * Class loading annotations from model elements.
 * @author Pavel
 *
 * @param <T>
 */
public class AnnotationSource<T extends EModelElement> {

	protected T modelElement;

	protected AnnotationSource(T modelElement) {
		this.modelElement = modelElement;
	}

	/**
	 * Source for Nasdanika HTML annotations.
	 */
	public static final String NASDANIKA_HTML_ANNOTATION_SOURCE = "org.nasdanika.html";	
	
	/**
	 * @return List of sources to use for loading annotations such as icon.
	 */
	protected List<String> getAnnotationSources() {
		return Collections.singletonList(NASDANIKA_HTML_ANNOTATION_SOURCE);
	}
	
	protected String getAnnotation(String key) {
		for (String annotationSource: getAnnotationSources()) {
			EAnnotation annotation = modelElement.getEAnnotation(annotationSource);
			if (annotation != null) {
				String details = annotation.getDetails().get(key);
				if (details != null) {
					return details;
				}
			}
		}
		return null;
	}		

}
