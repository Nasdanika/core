/**
 */
package org.nasdanika.party;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Markdown Text</b></em>'.
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
 *   <li>{@link org.nasdanika.party.MarkdownText#getMarkdown <em>Markdown</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getMarkdownText()
 * @model
 * @generated
 */
public interface MarkdownText extends Markdown {
	/**
	 * Returns the value of the '<em><b>Markdown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Markdown text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Markdown</em>' attribute.
	 * @see #setMarkdown(String)
	 * @see org.nasdanika.party.PartyPackage#getMarkdownText_Markdown()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika content-type='text/markdown'"
	 * @generated
	 */
	String getMarkdown();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.MarkdownText#getMarkdown <em>Markdown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Markdown</em>' attribute.
	 * @see #getMarkdown()
	 * @generated
	 */
	void setMarkdown(String value);

} // MarkdownText
