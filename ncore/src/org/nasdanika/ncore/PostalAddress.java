/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Postal Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getCountry <em>Country</em>}</li>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getStateProvince <em>State Province</em>}</li>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getCity <em>City</em>}</li>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getPostalCode <em>Postal Code</em>}</li>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getLine1 <em>Line1</em>}</li>
 *   <li>{@link org.nasdanika.ncore.PostalAddress#getLine2 <em>Line2</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress()
 * @model
 * @generated
 */
public interface PostalAddress extends ContactMethod {
	/**
	 * Returns the value of the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Country</em>' attribute.
	 * @see #setCountry(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_Country()
	 * @model
	 * @generated
	 */
	String getCountry();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getCountry <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Country</em>' attribute.
	 * @see #getCountry()
	 * @generated
	 */
	void setCountry(String value);

	/**
	 * Returns the value of the '<em><b>State Province</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Province</em>' attribute.
	 * @see #setStateProvince(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_StateProvince()
	 * @model
	 * @generated
	 */
	String getStateProvince();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getStateProvince <em>State Province</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Province</em>' attribute.
	 * @see #getStateProvince()
	 * @generated
	 */
	void setStateProvince(String value);

	/**
	 * Returns the value of the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>City</em>' attribute.
	 * @see #setCity(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_City()
	 * @model
	 * @generated
	 */
	String getCity();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getCity <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>City</em>' attribute.
	 * @see #getCity()
	 * @generated
	 */
	void setCity(String value);

	/**
	 * Returns the value of the '<em><b>Postal Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postal Code</em>' attribute.
	 * @see #setPostalCode(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_PostalCode()
	 * @model
	 * @generated
	 */
	String getPostalCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getPostalCode <em>Postal Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postal Code</em>' attribute.
	 * @see #getPostalCode()
	 * @generated
	 */
	void setPostalCode(String value);

	/**
	 * Returns the value of the '<em><b>Line1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line1</em>' attribute.
	 * @see #setLine1(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_Line1()
	 * @model
	 * @generated
	 */
	String getLine1();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getLine1 <em>Line1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line1</em>' attribute.
	 * @see #getLine1()
	 * @generated
	 */
	void setLine1(String value);

	/**
	 * Returns the value of the '<em><b>Line2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line2</em>' attribute.
	 * @see #setLine2(String)
	 * @see org.nasdanika.ncore.NcorePackage#getPostalAddress_Line2()
	 * @model
	 * @generated
	 */
	String getLine2();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.PostalAddress#getLine2 <em>Line2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line2</em>' attribute.
	 * @see #getLine2()
	 * @generated
	 */
	void setLine2(String value);

} // PostalAddress
