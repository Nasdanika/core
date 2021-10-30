/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

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
 *   <li>{@link org.nasdanika.flow.Participant#getSpecializations <em>Specializations</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getBaseKeys <em>Base Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getBases <em>Bases</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getAccountable <em>Accountable</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getConsulted <em>Consulted</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getInformed <em>Informed</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getChildren <em>Children</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Specializations</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getBases <em>Bases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specializations</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Specializations()
	 * @see org.nasdanika.flow.Participant#getBases
	 * @model opposite="bases" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getSpecializations();

	/**
	 * Returns the value of the '<em><b>Base Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of base participants, which this participant is a specialization of, relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_BaseKeys()
	 * @model annotation="urn:org.nasdanika feature-key='bases'"
	 * @generated
	 */
	EList<String> getBaseKeys();

	/**
	 * Returns the value of the '<em><b>Bases</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getSpecializations <em>Specializations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bases</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Bases()
	 * @see org.nasdanika.flow.Participant#getSpecializations
	 * @model opposite="specializations" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getBases();

	/**
	 * Returns the value of the '<em><b>Responsible</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.ParticipantResponsibility}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.ParticipantResponsibility#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Responsible()
	 * @see org.nasdanika.flow.ParticipantResponsibility#getResponsible
	 * @model opposite="responsible" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<ParticipantResponsibility<?>> getResponsible();

	/**
	 * Returns the value of the '<em><b>Accountable</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.ParticipantResponsibility}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.ParticipantResponsibility#getAccountable <em>Accountable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accountable</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Accountable()
	 * @see org.nasdanika.flow.ParticipantResponsibility#getAccountable
	 * @model opposite="accountable" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<ParticipantResponsibility<?>> getAccountable();

	/**
	 * Returns the value of the '<em><b>Consulted</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.ParticipantResponsibility}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.ParticipantResponsibility#getConsulted <em>Consulted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consulted</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Consulted()
	 * @see org.nasdanika.flow.ParticipantResponsibility#getConsulted
	 * @model opposite="consulted" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<ParticipantResponsibility<?>> getConsulted();

	/**
	 * Returns the value of the '<em><b>Informed</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.ParticipantResponsibility}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.ParticipantResponsibility#getInformed <em>Informed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Informed</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Informed()
	 * @see org.nasdanika.flow.ParticipantResponsibility#getInformed
	 * @model opposite="informed" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<ParticipantResponsibility<?>> getInformed();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Participant},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Participants can be organized into a hierarchy (Organizational Structure).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Children()
	 * @model mapType="org.nasdanika.flow.ParticipantEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Participant&gt;"
	 * @generated
	 */
	EMap<String, Participant> getChildren();

} // Participant
