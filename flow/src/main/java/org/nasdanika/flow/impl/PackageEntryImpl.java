/**
 */
package org.nasdanika.flow.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.PackageEntryImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageEntryImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageEntryImpl extends MinimalEObjectImpl.Container implements BasicEMap.Entry<String,org.nasdanika.flow.Package> {
	/**
	 * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PACKAGE_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypedKey() {
		return (String)eDynamicGet(FlowPackage.PACKAGE_ENTRY__KEY, FlowPackage.Literals.PACKAGE_ENTRY__KEY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypedKey(String newKey) {
		eDynamicSet(FlowPackage.PACKAGE_ENTRY__KEY, FlowPackage.Literals.PACKAGE_ENTRY__KEY, newKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.nasdanika.flow.Package getTypedValue() {
		return (org.nasdanika.flow.Package)eDynamicGet(FlowPackage.PACKAGE_ENTRY__VALUE, FlowPackage.Literals.PACKAGE_ENTRY__VALUE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypedValue(org.nasdanika.flow.Package newValue, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newValue, FlowPackage.PACKAGE_ENTRY__VALUE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypedValue(org.nasdanika.flow.Package newValue) {
		eDynamicSet(FlowPackage.PACKAGE_ENTRY__VALUE, FlowPackage.Literals.PACKAGE_ENTRY__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PACKAGE_ENTRY__VALUE:
				return basicSetTypedValue(null, msgs);
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
			case FlowPackage.PACKAGE_ENTRY__KEY:
				return getTypedKey();
			case FlowPackage.PACKAGE_ENTRY__VALUE:
				return getTypedValue();
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
			case FlowPackage.PACKAGE_ENTRY__KEY:
				setTypedKey((String)newValue);
				return;
			case FlowPackage.PACKAGE_ENTRY__VALUE:
				setTypedValue((org.nasdanika.flow.Package)newValue);
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
			case FlowPackage.PACKAGE_ENTRY__KEY:
				setTypedKey(KEY_EDEFAULT);
				return;
			case FlowPackage.PACKAGE_ENTRY__VALUE:
				setTypedValue((org.nasdanika.flow.Package)null);
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
			case FlowPackage.PACKAGE_ENTRY__KEY:
				return KEY_EDEFAULT == null ? getTypedKey() != null : !KEY_EDEFAULT.equals(getTypedKey());
			case FlowPackage.PACKAGE_ENTRY__VALUE:
				return getTypedValue() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected int hash = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHash() {
		if (hash == -1) {
			Object theKey = getKey();
			hash = (theKey == null ? 0 : theKey.hashCode());
		}
		return hash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHash(int hash) {
		this.hash = hash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getKey() {
		return getTypedKey();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKey(String key) {
		setTypedKey(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.flow.Package getValue() {
		return getTypedValue();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.flow.Package setValue(org.nasdanika.flow.Package value) {
		org.nasdanika.flow.Package oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EMap<String, org.nasdanika.flow.Package> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<String, org.nasdanika.flow.Package>)container.eGet(eContainmentFeature());
	}

} //PackageEntryImpl
