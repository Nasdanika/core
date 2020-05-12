/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.PostalAddress;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Postal Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getCountry <em>Country</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getStateProvince <em>State Province</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getCity <em>City</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getPostalCode <em>Postal Code</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getLine1 <em>Line1</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.PostalAddressImpl#getLine2 <em>Line2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PostalAddressImpl extends ContactMethodImpl implements PostalAddress {
	/**
	 * The default value of the '{@link #getCountry() <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountry()
	 * @generated
	 * @ordered
	 */
	protected static final String COUNTRY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getStateProvince() <em>State Province</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateProvince()
	 * @generated
	 * @ordered
	 */
	protected static final String STATE_PROVINCE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCity() <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCity()
	 * @generated
	 * @ordered
	 */
	protected static final String CITY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPostalCode() <em>Postal Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostalCode()
	 * @generated
	 * @ordered
	 */
	protected static final String POSTAL_CODE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLine1() <em>Line1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine1()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE1_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLine2() <em>Line2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine2()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE2_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostalAddressImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.POSTAL_ADDRESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCountry() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__COUNTRY, PartyPackage.Literals.POSTAL_ADDRESS__COUNTRY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCountry(String newCountry) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__COUNTRY, PartyPackage.Literals.POSTAL_ADDRESS__COUNTRY, newCountry);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStateProvince() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE, PartyPackage.Literals.POSTAL_ADDRESS__STATE_PROVINCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStateProvince(String newStateProvince) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE, PartyPackage.Literals.POSTAL_ADDRESS__STATE_PROVINCE, newStateProvince);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCity() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__CITY, PartyPackage.Literals.POSTAL_ADDRESS__CITY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCity(String newCity) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__CITY, PartyPackage.Literals.POSTAL_ADDRESS__CITY, newCity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPostalCode() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__POSTAL_CODE, PartyPackage.Literals.POSTAL_ADDRESS__POSTAL_CODE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPostalCode(String newPostalCode) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__POSTAL_CODE, PartyPackage.Literals.POSTAL_ADDRESS__POSTAL_CODE, newPostalCode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLine1() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__LINE1, PartyPackage.Literals.POSTAL_ADDRESS__LINE1, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLine1(String newLine1) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__LINE1, PartyPackage.Literals.POSTAL_ADDRESS__LINE1, newLine1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLine2() {
		return (String)eDynamicGet(PartyPackage.POSTAL_ADDRESS__LINE2, PartyPackage.Literals.POSTAL_ADDRESS__LINE2, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLine2(String newLine2) {
		eDynamicSet(PartyPackage.POSTAL_ADDRESS__LINE2, PartyPackage.Literals.POSTAL_ADDRESS__LINE2, newLine2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartyPackage.POSTAL_ADDRESS__COUNTRY:
				return getCountry();
			case PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE:
				return getStateProvince();
			case PartyPackage.POSTAL_ADDRESS__CITY:
				return getCity();
			case PartyPackage.POSTAL_ADDRESS__POSTAL_CODE:
				return getPostalCode();
			case PartyPackage.POSTAL_ADDRESS__LINE1:
				return getLine1();
			case PartyPackage.POSTAL_ADDRESS__LINE2:
				return getLine2();
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
			case PartyPackage.POSTAL_ADDRESS__COUNTRY:
				setCountry((String)newValue);
				return;
			case PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE:
				setStateProvince((String)newValue);
				return;
			case PartyPackage.POSTAL_ADDRESS__CITY:
				setCity((String)newValue);
				return;
			case PartyPackage.POSTAL_ADDRESS__POSTAL_CODE:
				setPostalCode((String)newValue);
				return;
			case PartyPackage.POSTAL_ADDRESS__LINE1:
				setLine1((String)newValue);
				return;
			case PartyPackage.POSTAL_ADDRESS__LINE2:
				setLine2((String)newValue);
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
			case PartyPackage.POSTAL_ADDRESS__COUNTRY:
				setCountry(COUNTRY_EDEFAULT);
				return;
			case PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE:
				setStateProvince(STATE_PROVINCE_EDEFAULT);
				return;
			case PartyPackage.POSTAL_ADDRESS__CITY:
				setCity(CITY_EDEFAULT);
				return;
			case PartyPackage.POSTAL_ADDRESS__POSTAL_CODE:
				setPostalCode(POSTAL_CODE_EDEFAULT);
				return;
			case PartyPackage.POSTAL_ADDRESS__LINE1:
				setLine1(LINE1_EDEFAULT);
				return;
			case PartyPackage.POSTAL_ADDRESS__LINE2:
				setLine2(LINE2_EDEFAULT);
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
			case PartyPackage.POSTAL_ADDRESS__COUNTRY:
				return COUNTRY_EDEFAULT == null ? getCountry() != null : !COUNTRY_EDEFAULT.equals(getCountry());
			case PartyPackage.POSTAL_ADDRESS__STATE_PROVINCE:
				return STATE_PROVINCE_EDEFAULT == null ? getStateProvince() != null : !STATE_PROVINCE_EDEFAULT.equals(getStateProvince());
			case PartyPackage.POSTAL_ADDRESS__CITY:
				return CITY_EDEFAULT == null ? getCity() != null : !CITY_EDEFAULT.equals(getCity());
			case PartyPackage.POSTAL_ADDRESS__POSTAL_CODE:
				return POSTAL_CODE_EDEFAULT == null ? getPostalCode() != null : !POSTAL_CODE_EDEFAULT.equals(getPostalCode());
			case PartyPackage.POSTAL_ADDRESS__LINE1:
				return LINE1_EDEFAULT == null ? getLine1() != null : !LINE1_EDEFAULT.equals(getLine1());
			case PartyPackage.POSTAL_ADDRESS__LINE2:
				return LINE2_EDEFAULT == null ? getLine2() != null : !LINE2_EDEFAULT.equals(getLine2());
		}
		return super.eIsSet(featureID);
	}

} //PostalAddressImpl
