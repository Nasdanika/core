/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Source of transitions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Source#getOutboundTransitions <em>Outbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Source#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getSource()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika label_ru='\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a' documentation_ru='\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a (\u0447\u0435\u0433\u043e?) \u0422\u043e\u0447\u043a\u0430 \u0432\u0445\u043e\u0434\u0430?'"
 * @generated
 */
public interface Source extends FlowElement {
	/**
	 * Returns the value of the '<em><b>Outbound Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outbound transitions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outbound Transitions</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getSource_OutboundTransitions()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0418\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435' documentation_ru='\u0418\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435 (\u0447\u0442\u043e?) \u0422\u043e\u0447\u043a\u0438 \u0432\u044b\u0445\u043e\u0434\u0430?'"
	 * @generated
	 */
	EList<Transition> getOutboundTransitions();

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Artifact#getProducers <em>Producers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts output/produced by this source.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outputs</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getSource_Outputs()
	 * @see org.nasdanika.rigel.Artifact#getProducers
	 * @model opposite="producers"
	 *        annotation="urn:org.nasdanika label_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442' documentation_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u0438\u043c\u044b\u0435 \u044d\u0442\u0438\u043c \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u043e\u043c'"
	 * @generated
	 */
	EList<Artifact> getOutputs();

} // Source
