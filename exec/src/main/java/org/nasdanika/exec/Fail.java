/**
 */
package org.nasdanika.exec;

import org.nasdanika.ncore.ModelElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fail</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Fail#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getFail()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/fail.md'"
 * @generated
 */
public interface Fail extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Message to output. Interpolated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.nasdanika.exec.ExecPackage#getFail_Message()
	 * @model annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Fail#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

} // Fail
