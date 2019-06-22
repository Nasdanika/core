package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;

/**
 * Wraps {@link EObject} into a context.
 * @author Pavel
 *
 */
public class EObjectContext implements Context {

	private EObject target;

	public EObjectContext(EObject target) {
		this.target = target;
	}

	@Override
	public Object get(String key) {
		int sIdx = key.indexOf('/');
		String featureName = sIdx == -1 ? key : key.substring(0, sIdx);
		EStructuralFeature feature = target.eClass().getEStructuralFeature(featureName);
		if (feature == null) {
			return null;
		}
		Object val = target.eGet(feature);
		if (val == null || sIdx == -1) {
			return val;
		}
		if (val instanceof EObject) {
			return new EObjectContext((EObject) val).get(key.substring(sIdx + 1));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> type) {
		if (target == null) {
			return null;
		}
		if (type.isInstance(target)) {
			return (T) target;
		}
		return (T) EcoreUtil.getRegisteredAdapter(target, type);
	}

}
