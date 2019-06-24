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
public class EModelElementAnnotationResourceLocator implements ResourceLocator {

	private String source;
	private Function<String, String> keyMapper;
	private EModelElement target;
	private Context chain;

	/**
	 * Creates a new instance retrieving resource strings from a given annotation source on a given model element.
	 * @param target {@link EModelElement} to get annotations from.
	 * @param source Source of annotations.
	 * @param keyMapper Key mapping function, can be null. E.g. to load Russian element descriptions the key mapper may replace ``description`` with ``description_ru``.
	 * @param chain Chain context to load resource from.
	 */
	public EModelElementAnnotationResourceLocator(EModelElement target, String source, Function<String,String> keyMapper, Context chain) {
		this.target = target;
		this.source = source;
		this.keyMapper = keyMapper;
		this.chain = chain == null ? Context.EMPTY_CONTEXT : chain;
	}

	@Override
	public Object get(String key) {
		EAnnotation ann = target.getEAnnotation(source);
		String effectiveKey = keyMapper == null ? key : keyMapper.apply(key);
		return ann == null ? chain.get(effectiveKey) : ann.getDetails().get(effectiveKey);
	}

	@Override
	public <T> T get(Class<T> type) {
		return null;
	}
	

}
