/**
 */
package org.nasdanika.party;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Html Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown) text.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.HtmlText#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getHtmlText()
 * @model
 * @generated
 */
public interface HtmlText extends Html {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * HTML content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see org.nasdanika.party.PartyPackage#getHtmlText_Content()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika content-type='text/html'"
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.HtmlText#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

} // HtmlText
