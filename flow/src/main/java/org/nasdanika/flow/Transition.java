/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Transition#getPayload <em>Payload</em>}</li>
 *   <li>{@link org.nasdanika.flow.Transition#getPayloadKeys <em>Payload Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.Transition#getTargetKey <em>Target Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.Transition#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getTransition()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/transition.md' load-doc-reference='doc/transition-load-doc.md'"
 * @generated
 */
public interface Transition extends PackageElement<Transition> {
	/**
	 * Returns the value of the '<em><b>Payload</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getPayloadFor <em>Payload For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts passed from the source element to the target element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Payload</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getTransition_Payload()
	 * @see org.nasdanika.flow.Artifact#getPayloadFor
	 * @model opposite="payloadFor" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getPayload();

	/**
	 * Returns the value of the '<em><b>Payload Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of payload artifacts relative to the containing package ``artifacts/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Payload Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getTransition_PayloadKeys()
	 * @model annotation="urn:org.nasdanika feature-key='payload'"
	 * @generated
	 */
	EList<String> getPayloadKeys();

	/**
	 * Returns the value of the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Key of transition target relative to the containing flow ``elements/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Key</em>' attribute.
	 * @see #setTargetKey(String)
	 * @see org.nasdanika.flow.FlowPackage#getTransition_TargetKey()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika feature-key='target' default-feature='true'"
	 * @generated
	 */
	String getTargetKey();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Transition#getTargetKey <em>Target Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Key</em>' attribute.
	 * @see #getTargetKey()
	 * @generated
	 */
	void setTargetKey(String value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transition target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getTransition_Target()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	FlowElement<?> getTarget();

} // Transition
