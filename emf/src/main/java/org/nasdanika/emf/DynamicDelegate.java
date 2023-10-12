package org.nasdanika.emf;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Base class for classes which delegate feature get/set/unset to an adapter
 * with ability to fall-back to default behavior. Set this class as Root Extends
 * Class in .genmodel file instead of MinimalEObjectImpl.Container.
 * 
 * This class allows to implemnt lazy/on-demand loading of features from external systems.
 */
public class DynamicDelegate extends MinimalEObjectImpl.Container {
	
	/**
	 * Returns {@link DynamicValueHolder} which calls this object's super.dynamicGet|Set|Unset. 
	 * It can be used by {@link Delegate} adapter to pass-through value retrieval for some features, set lazily retrieved features on get, and to push feature values, e.g. on external modifications.
	 * @return
	 */
	public DynamicValueHolder getDynamicValueHolder() {
		return new DynamicValueHolder() {

			@Override
			public Object dynamicGet(int dynamicFeatureID) {
				return DynamicDelegate.super.dynamicGet(dynamicFeatureID);
			}

			@Override
			public void dynamicSet(int dynamicFeatureID, Object newValue) {
				DynamicDelegate.super.dynamicSet(dynamicFeatureID, newValue);				
			}

			@Override
			public void dynamicUnset(int dynamicFeatureID) {
				DynamicDelegate.super.dynamicUnset(dynamicFeatureID);				
			}
			
		};
	}
	
	/**
	 * Adapter interface
	 */
	public interface Delegate extends DynamicValueHolder, Adapter {
		
	}

	public Object dynamicGet(int dynamicFeatureID) {
		Adapter delegate = EcoreUtil.getRegisteredAdapter(this, Delegate.class);
		if (delegate instanceof Delegate) {
			return ((Delegate) delegate).dynamicGet(dynamicFeatureID);
		}
		return super.dynamicGet(dynamicFeatureID);
	}

	public void dynamicSet(int dynamicFeatureID, Object newValue) {
		Adapter delegate = EcoreUtil.getRegisteredAdapter(this, Delegate.class);
		if (delegate instanceof Delegate) {
			((Delegate) delegate).dynamicSet(dynamicFeatureID, newValue);
		} else {
			super.dynamicSet(dynamicFeatureID, newValue);
		}
	}

	public void dynamicUnset(int dynamicFeatureID) {
		Adapter delegate = EcoreUtil.getRegisteredAdapter(this, Delegate.class);
		if (delegate instanceof Delegate) {
			((Delegate) delegate).dynamicUnset(dynamicFeatureID);
		} else {
			super.dynamicUnset(dynamicFeatureID);
		}
	}

}
