/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Artifact#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getRepositoryKeys <em>Repository Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getInputFor <em>Input For</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getOutputFor <em>Output For</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getPayloadFor <em>Payload For</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getResponseFor <em>Response For</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getUsedBy <em>Used By</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends PackageElement<Artifact> {
	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Resource#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Repositories()
	 * @see org.nasdanika.flow.Resource#getArtifacts
	 * @model opposite="artifacts" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Resource> getRepositories();

	/**
	 * Returns the value of the '<em><b>Repository Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of artifact's repositories relative to the containing package ``resources/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Repository Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_RepositoryKeys()
	 * @model annotation="urn:org.nasdanika load-key='repositories'"
	 * @generated
	 */
	EList<String> getRepositoryKeys();

	/**
	 * Returns the value of the '<em><b>Input For</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow elements which take this artifact as an input.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input For</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_InputFor()
	 * @see org.nasdanika.flow.FlowElement#getInputArtifacts
	 * @model opposite="inputArtifacts" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement<?>> getInputFor();

	/**
	 * Returns the value of the '<em><b>Output For</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getOutputArtifacts <em>Output Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow elements which output this artifact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Output For</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_OutputFor()
	 * @see org.nasdanika.flow.FlowElement#getOutputArtifacts
	 * @model opposite="outputArtifacts" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement<?>> getOutputFor();

	/**
	 * Returns the value of the '<em><b>Payload For</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Transition}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Transition#getPayload <em>Payload</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Derived opposite to Transition payload.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Payload For</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_PayloadFor()
	 * @see org.nasdanika.flow.Transition#getPayload
	 * @model opposite="payload" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Transition> getPayloadFor();

	/**
	 * Returns the value of the '<em><b>Response For</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Call}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Call#getResponse <em>Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Derived opposite to Call response.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Response For</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_ResponseFor()
	 * @see org.nasdanika.flow.Call#getResponse
	 * @model opposite="response" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Call> getResponseFor();

	/**
	 * Returns the value of the '<em><b>Used By</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Participants use this artifact in their activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used By</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_UsedBy()
	 * @see org.nasdanika.flow.Participant#getArtifacts
	 * @model opposite="artifacts" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getUsedBy();

} // Artifact
