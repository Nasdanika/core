/**
 */
package org.nasdanika.party.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.party.AbstractOrganizationalUnit;
import org.nasdanika.party.OrganizationalUnit;
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.Role;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organizational Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.OrganizationalUnitImpl#getOrganizationalUnits <em>Organizational Units</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.OrganizationalUnitImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrganizationalUnitImpl extends PartyImpl implements OrganizationalUnit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrganizationalUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.ORGANIZATIONAL_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<AbstractOrganizationalUnit> getOrganizationalUnits() {
		return (EList<AbstractOrganizationalUnit>)eDynamicGet(PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS, PartyPackage.Literals.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Role> getRoles() {
		return (EList<Role>)eDynamicGet(PartyPackage.ORGANIZATIONAL_UNIT__ROLES, PartyPackage.Literals.ORGANIZATIONAL_UNIT__ROLES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS:
				return ((InternalEList<?>)getOrganizationalUnits()).basicRemove(otherEnd, msgs);
			case PartyPackage.ORGANIZATIONAL_UNIT__ROLES:
				return ((InternalEList<?>)getRoles()).basicRemove(otherEnd, msgs);
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
			case PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS:
				return getOrganizationalUnits();
			case PartyPackage.ORGANIZATIONAL_UNIT__ROLES:
				return getRoles();
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
			case PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS:
				getOrganizationalUnits().clear();
				getOrganizationalUnits().addAll((Collection<? extends AbstractOrganizationalUnit>)newValue);
				return;
			case PartyPackage.ORGANIZATIONAL_UNIT__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
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
			case PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS:
				getOrganizationalUnits().clear();
				return;
			case PartyPackage.ORGANIZATIONAL_UNIT__ROLES:
				getRoles().clear();
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
			case PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS:
				return !getOrganizationalUnits().isEmpty();
			case PartyPackage.ORGANIZATIONAL_UNIT__ROLES:
				return !getRoles().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OrganizationalUnitImpl
