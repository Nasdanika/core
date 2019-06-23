package org.nasdanika.emf;

import java.util.function.Function;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ResourceLocator;

/**
 * Retrieves resource strings from {@link EAnnotation}s for a given {@link EModelElement}.
 * @author Pavel
 *
 */
public class EModelElementAnnotationResourceLocator implements ResourceLocator<EModelElement> {

	private String source;
	private Function<String, String> keyMapper;

	/**
	 * Creates a new instance retrieving resource strings from a given annotation source.
	 * @param source Source of annotations.
	 * @param keyMapper Key mapping function, can be null. E.g. to load Russian element descriptions the key mapper may replace ``description`` with ``description_ru``.
	 */
	public EModelElementAnnotationResourceLocator(String source, Function<String,String> keyMapper) {
		this.source = source;
		this.keyMapper = keyMapper;
	}
	
	@Override
	public Context get(EModelElement modelElement) {
		EAnnotation ann = modelElement.getEAnnotation(source);
		return ann == null ? null : new Context() {

			@Override
			public Object get(String key) {
				return ann.getDetails().get(keyMapper == null ? key : keyMapper.apply(key));
			}

			@Override
			public <T> T get(Class<T> type) {
				return null;
			}
			
		};
	}
	
	

}
