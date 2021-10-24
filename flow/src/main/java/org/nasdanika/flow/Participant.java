/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Participant#getParticipates <em>Participates</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getParticipant()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/participant.md'"
 * @generated
 */
public interface Participant extends ServiceProvider<Participant> {
	/**
	 * Returns the value of the '<em><b>Participates</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow elements this participant participates in.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participates</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Participates()
	 * @see org.nasdanika.flow.FlowElement#getParticipants
	 * @model opposite="participants" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement<?>> getParticipates();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Resource#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resources this participant uses in their activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Resources()
	 * @see org.nasdanika.flow.Resource#getUsedBy
	 * @model opposite="usedBy" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts this participant uses in their activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Artifacts()
	 * @see org.nasdanika.flow.Artifact#getUsedBy
	 * @model opposite="usedBy" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

} // Participant
