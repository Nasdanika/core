/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.party.EMail;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.EMailImpl#getEMailAddress <em>EMail Address</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EMailImpl extends ContactMethodImpl implements EMail {
	/**
	 * The default value of the '{@link #getEMailAddress() <em>EMail Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEMailAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String EMAIL_ADDRESS_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMailImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.EMAIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEMailAddress() {
		return (String)eDynamicGet(PartyPackage.EMAIL__EMAIL_ADDRESS, PartyPackage.Literals.EMAIL__EMAIL_ADDRESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEMailAddress(String newEMailAddress) {
		eDynamicSet(PartyPackage.EMAIL__EMAIL_ADDRESS, PartyPackage.Literals.EMAIL__EMAIL_ADDRESS, newEMailAddress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartyPackage.EMAIL__EMAIL_ADDRESS:
				return getEMailAddress();
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
			case PartyPackage.EMAIL__EMAIL_ADDRESS:
				setEMailAddress((String)newValue);
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
			case PartyPackage.EMAIL__EMAIL_ADDRESS:
				setEMailAddress(EMAIL_ADDRESS_EDEFAULT);
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
			case PartyPackage.EMAIL__EMAIL_ADDRESS:
				return EMAIL_ADDRESS_EDEFAULT == null ? getEMailAddress() != null : !EMAIL_ADDRESS_EDEFAULT.equals(getEMailAddress());
		}
		return super.eIsSet(featureID);
	}

} //EMailImpl
