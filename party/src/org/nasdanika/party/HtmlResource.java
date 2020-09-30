/**
 */
package org.nasdanika.party;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Html Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Loads HTML from a resource.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.HtmlResource#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getHtmlResource()
 * @model
 * @generated
 */
public interface HtmlResource extends Html {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * HTML resource location resolved relative to the model resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.party.PartyPackage#getHtmlResource_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.HtmlResource#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // HtmlResource
