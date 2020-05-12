/**
 */
package org.nasdanika.party.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.impl.NamedElementImpl;
import org.nasdanika.party.ContactMethod;
import org.nasdanika.party.Party;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Party</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.PartyImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PartyImpl#getContactMethods <em>Contact Methods</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PartyImpl extends NamedElementImpl implements Party {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PartyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.PARTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(PartyPackage.PARTY__ID, NcorePackage.Literals.ENTITY__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(PartyPackage.PARTY__ID, NcorePackage.Literals.ENTITY__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ContactMethod> getContactMethods() {
		return (EList<ContactMethod>)eDynamicGet(PartyPackage.PARTY__CONTACT_METHODS, PartyPackage.Literals.PARTY__CONTACT_METHODS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PartyPackage.PARTY__CONTACT_METHODS:
				return ((InternalEList<?>)getContactMethods()).basicRemove(otherEnd, msgs);
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
			case PartyPackage.PARTY__ID:
				return getId();
			case PartyPackage.PARTY__CONTACT_METHODS:
				return getContactMethods();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PartyPackage.PARTY__ID:
				setId((String)newValue);
				return;
			case PartyPackage.PARTY__CONTACT_METHODS:
				getContactMethods().clear();
				getContactMethods().addAll((Collection<? extends ContactMethod>)newValue);
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
			case PartyPackage.PARTY__ID:
				setId(ID_EDEFAULT);
				return;
			case PartyPackage.PARTY__CONTACT_METHODS:
				getContactMethods().clear();
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
			case PartyPackage.PARTY__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case PartyPackage.PARTY__CONTACT_METHODS:
				return !getContactMethods().isEmpty();
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
		if (baseClass == Entity.class) {
			switch (derivedFeatureID) {
				case PartyPackage.PARTY__ID: return NcorePackage.ENTITY__ID;
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
		if (baseClass == Entity.class) {
			switch (baseFeatureID) {
				case NcorePackage.ENTITY__ID: return PartyPackage.PARTY__ID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //PartyImpl
