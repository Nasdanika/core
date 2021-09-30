/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Call#getResponse <em>Response</em>}</li>
 *   <li>{@link org.nasdanika.flow.Call#getResponseKeys <em>Response Keys</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getCall()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/call.md'"
 * @generated
 */
public interface Call extends Transition {
	/**
	 * Returns the value of the '<em><b>Response</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Response artifacts passed back from the target to the source activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Response</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getCall_Response()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getResponse();

	/**
	 * Returns the value of the '<em><b>Response Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of response artifacts relative to the containing package ``artifacts/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Response Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getCall_ResponseKeys()
	 * @model annotation="urn:org.nasdanika load-key='response'"
	 * @generated
	 */
	EList<String> getResponseKeys();

} // Call
