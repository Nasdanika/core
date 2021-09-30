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
 *   <li>{@link org.nasdanika.flow.Participant#getServices <em>Services</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getParticipates <em>Participates</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getParticipant()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/participant.md'"
 * @generated
 */
public interface Participant extends PackageElement<Participant> {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Activity<?>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Services provided by a participant. Participant service activities imply the containing participant. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Services()
	 * @model mapType="org.nasdanika.flow.ActivityEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Activity&lt;?&gt;&gt;"
	 * @generated
	 */
	EMap<String, Activity<?>> getServices();

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

} // Participant
