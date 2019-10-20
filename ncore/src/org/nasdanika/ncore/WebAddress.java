/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.WebAddress#getUrl <em>Url</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getWebAddress()
 * @model
 * @generated
 */
public interface WebAddress extends ContactMethod {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.nasdanika.ncore.NcorePackage#getWebAddress_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.WebAddress#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

} // WebAddress
