/**
 */
package org.nasdanika.exec.content;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Text#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.Text#isInterpolate <em>Interpolate</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getText()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/content/text.md'"
 * @generated
 */
public interface Text extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see org.nasdanika.exec.content.ContentPackage#getText_Content()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Text#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

	/**
	 * Returns the value of the '<em><b>Interpolate</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If ``true`` (default), content is interpolated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interpolate</em>' attribute.
	 * @see #setInterpolate(boolean)
	 * @see org.nasdanika.exec.content.ContentPackage#getText_Interpolate()
	 * @model default="true"
	 * @generated
	 */
	boolean isInterpolate();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Text#isInterpolate <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpolate</em>' attribute.
	 * @see #isInterpolate()
	 * @generated
	 */
	void setInterpolate(boolean value);

} // Text
