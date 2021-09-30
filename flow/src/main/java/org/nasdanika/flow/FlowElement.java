/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

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
 *   <li>{@link org.nasdanika.flow.FlowElement#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getInvocations <em>Invocations</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getInputArtifactKeys <em>Input Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOutputArtifacts <em>Output Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOutputArtifactKeys <em>Output Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getParticipantKeys <em>Participant Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getResourceKeys <em>Resource Keys</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlowElement()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey-element.md'"
 * @generated
 */
public interface FlowElement<T extends FlowElement<T>> extends PackageElement<T> {
	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Transition},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activity outbound transitions to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outputs</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Outputs()
	 * @model mapType="org.nasdanika.flow.TransitionEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Transition&gt;"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EMap<String, Transition> getOutputs();

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transitions which have this flow element as a target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inputs</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Inputs()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Transition> getInputs();

	/**
	 * Returns the value of the '<em><b>Calls</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Call},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calls</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Calls()
	 * @model mapType="org.nasdanika.flow.CallEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Call&gt;"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EMap<String, Call> getCalls();

	/**
	 * Returns the value of the '<em><b>Invocations</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Call}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls which have this flow element as a target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Invocations</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Invocations()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Call> getInvocations();

	/**
	 * Returns the value of the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of input artifacts resolved relative to the containing package ``artifacts/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input Artifact Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_InputArtifactKeys()
	 * @model annotation="urn:org.nasdanika load-key='input-artifacts'"
	 * @generated
	 */
	EList<String> getInputArtifactKeys();

	/**
	 * Returns the value of the '<em><b>Input Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getInputFor <em>Input For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Input artifacts required to start working on this activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_InputArtifacts()
	 * @see org.nasdanika.flow.Artifact#getInputFor
	 * @model opposite="inputFor" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getInputArtifacts();

	/**
	 * Returns the value of the '<em><b>Output Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getOutputFor <em>Output For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Output artifacts of the activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Output Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_OutputArtifacts()
	 * @see org.nasdanika.flow.Artifact#getOutputFor
	 * @model opposite="outputFor" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getOutputArtifacts();

	/**
	 * Returns the value of the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of output artifacts resolved relative to the containing package ``artifacts/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Output Artifact Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_OutputArtifactKeys()
	 * @model annotation="urn:org.nasdanika load-key='output-artifacts'"
	 * @generated
	 */
	EList<String> getOutputArtifactKeys();

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getParticipates <em>Participates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Personas performing this journey element if it is an activity. Applies to all journey elements in order to group them into persona partitions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participants</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Participants()
	 * @see org.nasdanika.flow.Participant#getParticipates
	 * @model opposite="participates" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Participant Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of participants resolved relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_ParticipantKeys()
	 * @model annotation="urn:org.nasdanika load-key='participants'"
	 * @generated
	 */
	EList<String> getParticipantKeys();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Resources()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * Returns the value of the '<em><b>Resource Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of resources resolved relative to the containing package ``resources/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_ResourceKeys()
	 * @model annotation="urn:org.nasdanika load-key='resources'"
	 * @generated
	 */
	EList<String> getResourceKeys();

} // FlowElement
