/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.nasdanika.diagram.DiagramElement;

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
 *   <li>{@link org.nasdanika.flow.Artifact#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getOutboundRelationships <em>Outbound Relationships</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getInboundRelationships <em>Inbound Relationships</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#isPartition <em>Partition</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends ParticipantResponsibility<Artifact>, ServiceProvider<Artifact> {
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

	/**
	 * Returns the value of the '<em><b>Responsibilities</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.ArtifactParticipantResponsibility}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Responsibilities for this artifact at a flow element level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Responsibilities</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Responsibilities()
	 * @see org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifact
	 * @model opposite="artifact" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<ArtifactParticipantResponsibility> getResponsibilities();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Artifact},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts can be organized into a hierarchy (Product Breakdown Structure).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Children()
	 * @model mapType="org.nasdanika.flow.ArtifactEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Artifact&gt;"
	 * @generated
	 */
	EMap<String, Artifact> getChildren();

	/**
	 * Returns the value of the '<em><b>Outbound Relationships</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Relationship},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outbound relationships to other artifacts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outbound Relationships</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_OutboundRelationships()
	 * @model mapType="org.nasdanika.flow.RelationshipEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Relationship&gt;"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true' load-key='relationships'"
	 * @generated
	 */
	EMap<String, Relationship> getOutboundRelationships();

	/**
	 * Returns the value of the '<em><b>Inbound Relationships</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Relationship}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Relationship#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Relationships which have this artifact as a target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inbound Relationships</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_InboundRelationships()
	 * @see org.nasdanika.flow.Relationship#getTarget
	 * @model opposite="target" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Relationship> getInboundRelationships();

	/**
	 * Returns the value of the '<em><b>Partition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, this composite artifact shall be displayed as a partition on the parent component diagram.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Partition</em>' attribute.
	 * @see #setPartition(boolean)
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Partition()
	 * @model
	 * @generated
	 */
	boolean isPartition();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Artifact#isPartition <em>Partition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition</em>' attribute.
	 * @see #isPartition()
	 * @generated
	 */
	void setPartition(boolean value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Diagram element style for component diagrams.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style</em>' containment reference.
	 * @see #setStyle(DiagramElement)
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Style()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	DiagramElement getStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Artifact#getStyle <em>Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' containment reference.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(DiagramElement value);

} // Artifact
