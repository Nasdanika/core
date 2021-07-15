package org.nasdanika.emf.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Caches computed feature values. Clears the cache on notifications.
 * @author Pavel
 *
 */
public class FeatureCacheAdapter extends AdapterImpl implements FeatureCache {

	/**
	 * Identity based notification to clear all caches.
	 */
	public static final Notification CLEAR_CACHE = new NotificationImpl(Notification.EVENT_TYPE_COUNT + 1, null, null);
	
	private Map<EStructuralFeature, Object> cache = new HashMap<>();
		
	@Override
	public boolean isAdapterForType(Object type) {
		return FeatureCache.class == type;
	}
	
	// TODO - hit/miss ratio

	@SuppressWarnings("unchecked")
	@Override
	public <F extends EStructuralFeature, T> T get(F feature, BiFunction<EObject, F, T> computer) {
		return (T) cache.computeIfAbsent(feature, f -> computer.apply((EObject) getTarget(), feature));
	}
	
	@Override
	public void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		if (msg == CLEAR_CACHE) {
			cache.clear();
		}
	}

}
