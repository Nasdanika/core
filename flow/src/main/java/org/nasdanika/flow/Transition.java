/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.NamedElement;

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
 *   <li>{@link org.nasdanika.flow.Transition#isSuppress <em>Suppress</em>}</li>
 *   <li>{@link org.nasdanika.flow.Transition#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getTransition()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/transition.md'"
 * @generated
 */
public interface Transition extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Payload</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts passed from source to target activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Payload</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getTransition_Payload()
	 * @model
	 * @generated
	 */
	EList<Artifact> getPayload();

	/**
	 * Returns the value of the '<em><b>Suppress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Can be used in journeys extending other journeys to remove the inherited transition from the list of outputs/call. Transitions to supprressed journey elements are automatically supppressed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Suppress</em>' attribute.
	 * @see #setSuppress(boolean)
	 * @see org.nasdanika.flow.FlowPackage#getTransition_Suppress()
	 * @model
	 * @generated
	 */
	boolean isSuppress();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Transition#isSuppress <em>Suppress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suppress</em>' attribute.
	 * @see #isSuppress()
	 * @generated
	 */
	void setSuppress(boolean value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Relative path to the target [journey element](JourneyElement.html). May contain ``..`` to navigate to the parent [journey](Journey.html) - i.e. the journey which contains the journey containing the element which contains this transition. May contain ``/`` to navigate to nested journeys. Treated as URI if contains ``:``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.nasdanika.flow.FlowPackage#getTransition_Target()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Transition#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolves transition's target journey element for a given journey.
	 * @param journeyPath Journey nesting path to resolve target element for. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	FlowElement getTarget(EList<Flow> journeyPath);

} // Transition
