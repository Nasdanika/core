/**
 */
package org.nasdanika.party;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phone</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Phone.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Phone#getCountryCode <em>Country Code</em>}</li>
 *   <li>{@link org.nasdanika.party.Phone#getAreaCode <em>Area Code</em>}</li>
 *   <li>{@link org.nasdanika.party.Phone#getPhoneNumber <em>Phone Number</em>}</li>
 *   <li>{@link org.nasdanika.party.Phone#getExtension <em>Extension</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getPhone()
 * @model
 * @generated
 */
public interface Phone extends ContactMethod {
	/**
	 * Returns the value of the '<em><b>Country Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Country code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Country Code</em>' attribute.
	 * @see #setCountryCode(int)
	 * @see org.nasdanika.party.PartyPackage#getPhone_CountryCode()
	 * @model
	 * @generated
	 */
	int getCountryCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Phone#getCountryCode <em>Country Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Country Code</em>' attribute.
	 * @see #getCountryCode()
	 * @generated
	 */
	void setCountryCode(int value);

	/**
	 * Returns the value of the '<em><b>Area Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Area code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Area Code</em>' attribute.
	 * @see #setAreaCode(int)
	 * @see org.nasdanika.party.PartyPackage#getPhone_AreaCode()
	 * @model
	 * @generated
	 */
	int getAreaCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Phone#getAreaCode <em>Area Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area Code</em>' attribute.
	 * @see #getAreaCode()
	 * @generated
	 */
	void setAreaCode(int value);

	/**
	 * Returns the value of the '<em><b>Phone Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Phone number.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phone Number</em>' attribute.
	 * @see #setPhoneNumber(int)
	 * @see org.nasdanika.party.PartyPackage#getPhone_PhoneNumber()
	 * @model
	 * @generated
	 */
	int getPhoneNumber();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Phone#getPhoneNumber <em>Phone Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phone Number</em>' attribute.
	 * @see #getPhoneNumber()
	 * @generated
	 */
	void setPhoneNumber(int value);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional extension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extension</em>' attribute.
	 * @see #setExtension(int)
	 * @see org.nasdanika.party.PartyPackage#getPhone_Extension()
	 * @model
	 * @generated
	 */
	int getExtension();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Phone#getExtension <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension</em>' attribute.
	 * @see #getExtension()
	 * @generated
	 */
	void setExtension(int value);

} // Phone
