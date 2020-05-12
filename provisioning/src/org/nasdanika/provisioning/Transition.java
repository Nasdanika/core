/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Transition from its containing source element to a target element. May pass artifacts in both directions. E.g. a transition to "Commit code" may take "Source code" as input and return "Commit ID" as a result.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Transition#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Transition#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Transition#getResults <em>Results</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getTransition()
 * @model annotation="urn:org.nasdanika label_ru='\u041f\u0435\u0440\u0435\u0445\u043e\u0434 (\u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0430)' documentation_ru='\u041f\u0435\u0440\u0435\u0445\u043e\u0434 (\u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0430) \u043f\u0440\u043e\u043c\u0435\u0436\u0443\u0442\u043e\u0447\u043d\u044b\u0445 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u043e\u0432 \u0440\u0430\u0431\u043e\u0442/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0439 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u043a \u0446\u0435\u043b\u0435\u0432\u043e\u043c\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443. \u0410\u0440\u0442\u0438\u0444\u0430\u043a\u0442\u044b \u043c\u043e\u0433\u0443\u0442 \u043f\u0435\u0440\u0435\u0434\u0430\u0432\u0430\u0442\u044c\u0441\u044f \u0432 \u043e\u0431\u043e\u0438\u0445 \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u044f\u0445. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u043f\u0435\u0440\u0435\u0445\u043e\u0434 \u043a \253\u0424\u0438\u043a\u0441\u0438\u0440\u0443\u044e\u0449\u0435\u043c\u0443 \u043a\u043e\u0434\u0443\273 \u043c\u043e\u0436\u0435\u0442 \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u044c \253\u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439 \u043a\u043e\u0434\273 \u0432 \u043a\u0430\u0447\u0435\u0441\u0442\u0432\u0435 \u0432\u0432\u043e\u0434\u0430 \u0438 \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \253\u0424\u0438\u043a\u0441\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0439 \u043a\u043e\u0434\273 \u0432 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u0435.\n '"
 * @generated
 */
public interface Transition extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Target#getInboundTransitions <em>Inbound Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transition target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Target)
	 * @see org.nasdanika.rigel.RigelPackage#getTransition_Target()
	 * @see org.nasdanika.rigel.Target#getInboundTransitions
	 * @model opposite="inboundTransitions" required="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0426\u0435\u043b\u0435\u0432\u043e\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442' documentation_ru='\u0420\u0430\u0431\u043e\u0442\u0430/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f, \u044f\u0432\u043b\u044f\u044e\u0449\u0430\u044f\u0441\u044f \u0446\u0435\u043b\u044c\u044e \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u0430, \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u043a\u043e\u0442\u043e\u0440\u043e\u0439 \u043d\u0435\u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e \u0434\u043e \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u0430.'"
	 * @generated
	 */
	Target getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Transition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Target value);

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts passed by the source to the transition and delivered to the target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inputs</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getTransition_Inputs()
	 * @model annotation="urn:org.nasdanika label_ru='\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b' documentation_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0440\u0435\u0434\u0430\u0432\u0430\u0435\u043c\u044b\u0435 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u043a \u0446\u0435\u043b\u0435\u0432\u043e\u043c\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443 \u0447\u0435\u0440\u0435\u0437 \u043f\u0435\u0440\u0435\u0445\u043e\u0434.'"
	 * @generated
	 */
	EList<Artifact> getInputs();

	/**
	 * Returns the value of the '<em><b>Results</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts returned by the target to be delivered to the source.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Results</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getTransition_Results()
	 * @model annotation="urn:org.nasdanika label_ru='\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442 \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438' documentation_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u043c\u044b\u0435 \u0446\u0435\u043b\u0435\u0432\u044b\u043c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443 \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0443.'"
	 * @generated
	 */
	EList<Artifact> getResults();

} // Transition
