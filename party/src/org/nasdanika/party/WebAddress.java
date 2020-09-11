/**
 */
package org.nasdanika.party;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * URL.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.WebAddress#getUrl <em>Url</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getWebAddress()
 * @model
 * @generated
 */
public interface WebAddress extends ContactMethod {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * URL.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.nasdanika.party.PartyPackage#getWebAddress_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.WebAddress#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

} // WebAddress
