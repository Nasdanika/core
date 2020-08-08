/**
 */
package org.nasdanika.ncore.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.ncore.AbstractEntry;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ListImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ListImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListImpl extends ArrayImpl implements List {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(NcorePackage.LIST__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(NcorePackage.LIST__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabled() {
		return (Boolean)eDynamicGet(NcorePackage.LIST__ENABLED, NcorePackage.Literals.ABSTRACT_ENTRY__ENABLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(boolean newEnabled) {
		eDynamicSet(NcorePackage.LIST__ENABLED, NcorePackage.Literals.ABSTRACT_ENTRY__ENABLED, newEnabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.LIST__NAME:
				return getName();
			case NcorePackage.LIST__ENABLED:
				return isEnabled();
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
			case NcorePackage.LIST__NAME:
				setName((String)newValue);
				return;
			case NcorePackage.LIST__ENABLED:
				setEnabled((Boolean)newValue);
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
			case NcorePackage.LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NcorePackage.LIST__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
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
			case NcorePackage.LIST__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case NcorePackage.LIST__ENABLED:
				return isEnabled() != ENABLED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case NcorePackage.LIST__NAME: return NcorePackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == AbstractEntry.class) {
			switch (derivedFeatureID) {
				case NcorePackage.LIST__ENABLED: return NcorePackage.ABSTRACT_ENTRY__ENABLED;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.NAMED_ELEMENT__NAME: return NcorePackage.LIST__NAME;
				default: return -1;
			}
		}
		if (baseClass == AbstractEntry.class) {
			switch (baseFeatureID) {
				case NcorePackage.ABSTRACT_ENTRY__ENABLED: return NcorePackage.LIST__ENABLED;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ListImpl
