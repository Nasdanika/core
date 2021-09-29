/**
 */
package org.nasdanika.flow.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ServiceImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ServiceImpl#getTargetKey <em>Target Key</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceImpl extends FlowElementImpl<Service> implements Service {
	/**
	 * The default value of the '{@link #getTargetKey() <em>Target Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetKey()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_KEY_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Activity<?> getTarget() {
		String targetKey = getTargetKey();
		if (Util.isBlank(targetKey)) {
			return null;
		}
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res == null) {
			return null;
		}
		ResourceSet resourceSet = res.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (ancestor instanceof org.nasdanika.flow.Package) {
				URI pkgURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/");
				URI activityURI = URI.createURI(targetKey).resolve(pkgURI);
				EObject target = resourceSet.getEObject(activityURI, false);
				if (target == null) {
					throw new ConfigurationException("Invalid activity reference: " + targetKey + " (" + activityURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
				}
				
				if (target instanceof Activity) {
					return (Activity<?>) target;
				}
				
				throw new ConfigurationException("Expected artifact at: " + targetKey + " (" + activityURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity<?> basicGetTarget() {
		return (Activity<?>)eDynamicGet(FlowPackage.SERVICE__TARGET, FlowPackage.Literals.SERVICE__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Activity<?> newTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTarget, FlowPackage.SERVICE__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetKey() {
		return (String)eDynamicGet(FlowPackage.SERVICE__TARGET_KEY, FlowPackage.Literals.SERVICE__TARGET_KEY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetKey(String newTargetKey) {
		eDynamicSet(FlowPackage.SERVICE__TARGET_KEY, FlowPackage.Literals.SERVICE__TARGET_KEY, newTargetKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET:
				Activity<?> target = basicGetTarget();
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, FlowPackage.ACTIVITY__SERVICES, Activity.class, msgs);
				return basicSetTarget((Activity<?>)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET:
				return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case FlowPackage.SERVICE__TARGET_KEY:
				return getTargetKey();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET_KEY:
				setTargetKey((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET_KEY:
				setTargetKey(TARGET_KEY_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FlowPackage.SERVICE__TARGET:
				return basicGetTarget() != null;
			case FlowPackage.SERVICE__TARGET_KEY:
				return TARGET_KEY_EDEFAULT == null ? getTargetKey() != null : !TARGET_KEY_EDEFAULT.equals(getTargetKey());
		}
		return super.eIsSet(featureID);
	}

} //ServiceImpl
