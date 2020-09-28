/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.party.OrganizationalUnit;
import org.nasdanika.party.OrganizationalUnitReference;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organizational Unit Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.OrganizationalUnitReferenceImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrganizationalUnitReferenceImpl extends AbstractOrganizationalUnitImpl implements OrganizationalUnitReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrganizationalUnitReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.ORGANIZATIONAL_UNIT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrganizationalUnit getTarget() {
		return (OrganizationalUnit)eDynamicGet(PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, PartyPackage.Literals.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationalUnit basicGetTarget() {
		return (OrganizationalUnit)eDynamicGet(PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, PartyPackage.Literals.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(OrganizationalUnit newTarget) {
		eDynamicSet(PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, PartyPackage.Literals.ORGANIZATIONAL_UNIT_REFERENCE__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET:
				setTarget((OrganizationalUnit)newValue);
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
			case PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET:
				setTarget((OrganizationalUnit)null);
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
			case PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //OrganizationalUnitReferenceImpl
