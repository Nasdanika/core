/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getDeliverables <em>Deliverables</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlowElement()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey-element.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='final override suppress suppressAndOverride'"
 * @generated
 */
public interface FlowElement<T extends FlowElement<T>> extends PackageElement<T> {
	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activity outbound transitions to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Outputs()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Transition> getOutputs();

	/**
	 * Returns the value of the '<em><b>Calls</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Call}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calls</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Calls()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Call> getCalls();

	/**
	 * Returns the value of the '<em><b>Input Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Input artifacts required to start working on this activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_InputArtifacts()
	 * @model
	 * @generated
	 */
	EList<Artifact> getInputArtifacts();

	/**
	 * Returns the value of the '<em><b>Deliverables</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Output artifacts of the activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deliverables</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Deliverables()
	 * @model
	 * @generated
	 */
	EList<Artifact> getDeliverables();

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Personas performing this journey element if it is an activity. Applies to all journey elements in order to group them into persona partitions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participants</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Participants()
	 * @model
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Resources()
	 * @model
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transitions which have this journey element as its target in a given journey path. 
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getInputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls which have this journey element as its target in a given journey path. 
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getInvocations(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opposite to all outputs, i.e. all inbound transitions, in a given journey path.
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getAllInputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opposite to all calls, i.e. all incoming calls, in a given journey path.
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getAllInvocations(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Includes own outputs and outputs inherited from the base journey of the containing journey. This element outputs override inherited outputs with the same path.
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getAllOutputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Includes own calls and calls inherited from the base journey of the containing journey. This element calls override inherited calls with the same path.
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getAllCalls(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns ``true`` if this journey element directly or transitively overrided the argument journey element.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean overrides(FlowElement<?> journeyElement);

} // FlowElement
