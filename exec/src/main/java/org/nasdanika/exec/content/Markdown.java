/**
 */
package org.nasdanika.exec.content;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Markdown</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Markdown#isStyle <em>Style</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getMarkdown()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/package-summary.md'"
 * @generated
 */
public interface Markdown extends Filter {
	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see #setStyle(boolean)
	 * @see org.nasdanika.exec.content.ContentPackage#getMarkdown_Style()
	 * @model
	 * @generated
	 */
	boolean isStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Markdown#isStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see #isStyle()
	 * @generated
	 */
	void setStyle(boolean value);

} // Markdown
