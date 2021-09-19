/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Participant#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.Participant#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getParticipant()
 * @model
 * @generated
 */
public interface Participant extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Extends()
	 * @see org.nasdanika.flow.Participant#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	EList<Participant> getExtends();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journeys extending this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extensions</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Extensions()
	 * @see org.nasdanika.flow.Participant#getExtends
	 * @model opposite="extends" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getExtensions();

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Activity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getParticipant_Services()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity> getServices();

} // Participant
