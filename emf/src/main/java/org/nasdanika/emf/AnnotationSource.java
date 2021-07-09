package org.nasdanika.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.MarkdownHelper;

/**
 * Class loading annotations from model elements.
 * @author Pavel
 *
 * @param <T>
 */
public class AnnotationSource<T extends EModelElement> extends MarkdownHelper {

	protected T modelElement;

	protected AnnotationSource(T modelElement) {
		this.modelElement = modelElement;
	}

	/**
	 * Source for Nasdanika annotations.
	 */
	public static final String NASDANIKA_ANNOTATION_SOURCE = "urn:org.nasdanika";	
	
	/**
	 * @return List of sources to use for loading annotations such as icon.
	 */
	protected List<String> getAnnotationSources() {
		return Collections.singletonList(NASDANIKA_ANNOTATION_SOURCE);
	}
	
	public String getAnnotation(String key) {
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
