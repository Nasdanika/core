/**
 */
package org.nasdanika.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pseudo State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.PseudoState#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getPseudoState()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/pseudo-state.md'"
 * @generated
 */
public interface PseudoState extends FlowElement<PseudoState> {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Pseudo-state type provided by concrete sub-classes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.nasdanika.flow.FlowPackage#getPseudoState_Type()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	String getType();

} // PseudoState
