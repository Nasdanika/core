package org.nasdanika.emf;

import java.util.function.Function;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.ResourceLocator;

/**
 * Retrieves resource strings from {@link EAnnotation}s for a given {@link EModelElement}.
 * @author Pavel
 *
 */
public class EModelElementAnnotationResourceLocator implements ResourceLocator {

	private String source;
	private Function<String, String> keyMapper;
	private EModelElement target;

	/**
	 * Creates a new instance retrieving resource strings from a given annotation source on a given model element.
	 * @param target {@link EModelElement} to get annotations from.
	 * @param source Source of annotations.
	 * @param keyMapper Key mapping function, can be null. E.g. to load Russian element descriptions the key mapper may replace ``description`` with ``description_ru``.
	 */
	public EModelElementAnnotationResourceLocator(EModelElement target, String source, Function<String,String> keyMapper) {
		this.target = target;
		this.source = source;
		this.keyMapper = keyMapper;
	}

	@Override
	public Object get(String key) {
		EAnnotation ann = target.getEAnnotation(source);
		return ann == null ? null : ann.getDetails().get(keyMapper == null ? key : keyMapper.apply(key));
	}

	@Override
	public <T> T get(Class<T> type) {
		return null;
	}
	

}
