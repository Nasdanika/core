/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Target of transitions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Target#getInboundTransitions <em>Inbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Target#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getTarget()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika label_ru='\u0426\u0435\u043b\u044c' documentation_ru='\u0426\u0435\u043b\u044c \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438 \u043f\u043e\u0442\u043e\u043a\u0430 \u0434\u0430\u043d\u043d\u044b\u0445 \u0438  \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u043e\u0432'"
 * @generated
 */
public interface Target extends FlowElement {
	/**
	 * Returns the value of the '<em><b>Inbound Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Transition}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Inbound transitions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inbound Transitions</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getTarget_InboundTransitions()
	 * @see org.nasdanika.rigel.Transition#getTarget
	 * @model opposite="target"
	 *        annotation="urn:org.nasdanika label_ru='\u0412\u0445\u043e\u0434 \u0432 \u0440\u0430\u0431\u043e\u0442\u0443/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044e' documentation_ru='???Inbound transitions.'"
	 * @generated
	 */
	EList<Transition> getInboundTransitions();

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Artifact#getConsumers <em>Consumers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Input artifacts consumed by this target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inputs</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getTarget_Inputs()
	 * @see org.nasdanika.rigel.Artifact#getConsumers
	 * @model opposite="consumers"
	 *        annotation="urn:org.nasdanika label_ru='\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b' documentation_ru='\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043d\u0435\u043e\u0431\u0445\u043e\u0434\u0438\u043c\u044b\u0435 \u0434\u043b\u044f \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0446\u0435\u043b\u0435\u0432\u043e\u0433\u043e \u044d\u0442\u0430\u043f\u0430 \u0440\u0430\u0431\u043e\u0442\u044b'"
	 * @generated
	 */
	EList<Artifact> getInputs();

} // Target
