package org.nasdanika.emf.persistence;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.emf.EObjectAdaptable;

/**
 * Computes feature value and caches. 
 * This is an adapter interface.
 * @author Pavel
 *
 */
public interface FeatureCache {
	
	<F extends EStructuralFeature, T> T get(F feature, BiFunction<EObject, F, T> computer);
	
	/**
	 * Adapts to FeatureCache and gets from cache if it is present or from the computer otherwise.
	 * @param <F>
	 * @param <T>
	 * @param target
	 * @param feature
	 * @param computer
	 * @param add If true and adapter is not present, adds the adapter.
	 */
	static <F extends EStructuralFeature, T> T get(EObject target, F feature, BiFunction<EObject, F, T> computer, boolean add) {
		FeatureCache adapter = EObjectAdaptable.adaptTo(target, FeatureCache.class);
		if (adapter == null) {
			if (add) {
				adapter = new FeatureCacheAdapter();
				target.eAdapters().add((FeatureCacheAdapter) adapter);
			} else {
				return computer.apply(target, feature);
			}
		}
		return adapter.get(feature, computer);
	}
	
	/**
	 * Adapts to FeatureCache and gets from cache if it is present or from the computer otherwise.
	 * @param <F>
	 * @param <T>
	 * @param target
	 * @param feature
	 * @param computer
	 * @param add If true and adapter is not present, adds the adapter.
	 */
	static <F extends EStructuralFeature, T> T get(EObject target, F feature, Supplier<T> computer, boolean add) {
		return get(target, feature, (t,f) -> computer.get(), add);
	}
	
}
