/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant Responsibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getResponsibleKeys <em>Responsible Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getAccountable <em>Accountable</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getAccountableKeys <em>Accountable Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getConsulted <em>Consulted</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getConsultedKeys <em>Consulted Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getInformed <em>Informed</em>}</li>
 *   <li>{@link org.nasdanika.flow.ParticipantResponsibility#getInformedKeys <em>Informed Keys</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility()
 * @model
 * @generated
 */
public interface ParticipantResponsibility<T extends PackageElement<T>> extends PackageElement<T> {

	/**
	 * Returns the value of the '<em><b>Responsible</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_Responsible()
	 * @see org.nasdanika.flow.Participant#getResponsible
	 * @model opposite="responsible" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getResponsible();

	/**
	 * Returns the value of the '<em><b>Responsible Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of responsible participants relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Responsible Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_ResponsibleKeys()
	 * @model annotation="urn:org.nasdanika feature-key='responsible'"
	 * @generated
	 */
	EList<String> getResponsibleKeys();

	/**
	 * Returns the value of the '<em><b>Accountable</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getAccountable <em>Accountable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accountable</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_Accountable()
	 * @see org.nasdanika.flow.Participant#getAccountable
	 * @model opposite="accountable" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getAccountable();

	/**
	 * Returns the value of the '<em><b>Accountable Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of accountable participants relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Accountable Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_AccountableKeys()
	 * @model annotation="urn:org.nasdanika feature-key='accountable'"
	 * @generated
	 */
	EList<String> getAccountableKeys();

	/**
	 * Returns the value of the '<em><b>Consulted</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getConsulted <em>Consulted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consulted</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_Consulted()
	 * @see org.nasdanika.flow.Participant#getConsulted
	 * @model opposite="consulted" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getConsulted();

	/**
	 * Returns the value of the '<em><b>Consulted Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of consulted participants relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Consulted Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_ConsultedKeys()
	 * @model annotation="urn:org.nasdanika feature-key='consulted'"
	 * @generated
	 */
	EList<String> getConsultedKeys();

	/**
	 * Returns the value of the '<em><b>Informed</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getInformed <em>Informed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Informed</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_Informed()
	 * @see org.nasdanika.flow.Participant#getInformed
	 * @model opposite="informed" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getInformed();

	/**
	 * Returns the value of the '<em><b>Informed Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of informed participants relative to the containing package ``participants/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Informed Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipantResponsibility_InformedKeys()
	 * @model annotation="urn:org.nasdanika feature-key='informed'"
	 * @generated
	 */
	EList<String> getInformedKeys();
} // ParticipantResponsibility
