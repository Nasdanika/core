/**
 */
package org.nasdanika.party;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Markdown Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown) resource.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.MarkdownResource#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getMarkdownResource()
 * @model
 * @generated
 */
public interface MarkdownResource extends Markdown {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Markdown resource location. The resource location is resolved relative to the model resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.party.PartyPackage#getMarkdownResource_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.MarkdownResource#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // MarkdownResource
