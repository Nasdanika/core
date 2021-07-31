/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Eval</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Eval#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.exec.Eval#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getEval()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/eval.md'"
 * @generated
 */
public interface Eval extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script source
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(EObject)
	 * @see org.nasdanika.exec.ExecPackage#getEval_Script()
	 * @model containment="true" required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	EObject getScript();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Eval#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(EObject value);

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script bindings. Context is available as ``context`` binding and progress monitor as ``progressMonitor`` binding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bindings</em>' map.
	 * @see org.nasdanika.exec.ExecPackage#getEval_Bindings()
	 * @model mapType="org.nasdanika.exec.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject&gt;"
	 * @generated
	 */
	EMap<String, EObject> getBindings();

} // Eval
