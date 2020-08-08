/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Evaluates script code.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ScriptText#getCode <em>Code</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getScriptText()
 * @model
 * @generated
 */
public interface ScriptText extends Script {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * JavaScript code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see org.nasdanika.ncore.NcorePackage#getScriptText_Code()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika content-type='text/code'"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ScriptText#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

} // ScriptText
