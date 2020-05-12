/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Artifact is something output/produced by a source and consumed by a target as input. For example - source code, user guide, binary code. Artifacts are passed between activities over transitions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Artifact#getConsumers <em>Consumers</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Artifact#getProducers <em>Producers</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Artifact#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getArtifact()
 * @model annotation="urn:org.nasdanika label_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442' documentation_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442 - \u044d\u0442\u043e \u0447\u0442\u043e-\u0442\u043e, \u0447\u0442\u043e \u0432\u044b\u0432\u043e\u0434\u0438\u0442\u0441\u044f / \u0433\u0435\u043d\u0435\u0440\u0438\u0440\u0443\u0435\u0442\u0441\u044f \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u043e\u043c \u0438 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442\u0441\u044f \u0446\u0435\u043b\u044c\u044e \u0432 \u043a\u0430\u0447\u0435\u0441\u0442\u0432\u0435 \u0432\u0445\u043e\u0434\u043d\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0445. \n\n\u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440 - \u0438\u0441\u0445\u043e\u0434\u043d\u044b\u0439 \u043a\u043e\u0434, \u0440\u0443\u043a\u043e\u0432\u043e\u0434\u0441\u0442\u0432\u043e \u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044f, \u0434\u0432\u043e\u0438\u0447\u043d\u044b\u0439 \u043a\u043e\u0434. \n\n*\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442 - \u044d\u0442\u043e \u043f\u0440\u043e\u043c\u0435\u0436\u0443\u0442\u043e\u0447\u043d\u044b\u0439 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442, \u043a\u043e\u0442\u043e\u0440\u044b\u0439 \u043f\u0435\u0440\u0435\u0434\u0430\u0435\u0442\u0441\u044f \u043e\u0442 \u043f\u0440\u0435\u0434\u0448\u0435\u0441\u0442\u0432\u0443\u044e\u0449\u0435\u0439 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 (\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430) \u043a \u043f\u043e\u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0435\u0439 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 (\u0446\u0435\u043b\u0438).*\n\n\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0435\u0440\u0435\u0434\u0430\u044e\u0442\u0441\u044f \u043c\u0435\u0436\u0434\u0443 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f\u043c\u0438 \u0447\u0435\u0440\u0435\u0437 \u043f\u0435\u0440\u0435\u0445\u043e\u0434\u044b.'"
 * @generated
 */
public interface Artifact extends PackageElement, EngineeredElement {
	/**
	 * Returns the value of the '<em><b>Consumers</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Target}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Target#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Targets consuming this artifact as their input.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Consumers</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getArtifact_Consumers()
	 * @see org.nasdanika.rigel.Target#getInputs
	 * @model opposite="inputs"
	 *        annotation="urn:org.nasdanika label_ru='\u041f\u043e\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043b\u0438' documentation_ru='\u0420\u0430\u0431\u043e\u0442\u044b \u0438\u043b\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0434\u043b\u044f \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0439 \u0430\u0440\u0438\u0442\u0435\u0444\u0430\u043a\u0442 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0432\u0445\u043e\u0434\u044f\u0449\u0438\u043c.'"
	 * @generated
	 */
	EList<Target> getConsumers();

	/**
	 * Returns the value of the '<em><b>Producers</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Source}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Source#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sources emitting this artifact as their output.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Producers</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getArtifact_Producers()
	 * @see org.nasdanika.rigel.Source#getOutputs
	 * @model opposite="outputs"
	 *        annotation="urn:org.nasdanika label_ru='\u041f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u0438\u0442\u0435\u043b\u0438' documentation_ru='\u0420\u0430\u0431\u043e\u0442\u0430 \u0438\u043b\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u043e\u043c \u043a\u043e\u0442\u043e\u0440\u043e\u0439 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0434\u0430\u043d\u043d\u044b\u0439 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442'"
	 * @generated
	 */
	EList<Source> getProducers();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts can be nested. E.g. a zip archive contains directories which contain files.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getArtifact_Children()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0412\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b' documentation_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043c\u043e\u0433\u0443\u0442 \u0431\u044b\u0442\u044c \u0432\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u043c\u0438 \u0434\u0440\u0443\u0433 \u0432 \u0434\u0440\u0443\u0433\u0430. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440: zip, \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0449\u0438\u0439 \u043f\u0430\u043f\u043a\u0438, \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0449\u0438\u0435 \u0444\u0430\u0439\u043b\u044b.'"
	 * @generated
	 */
	EList<Artifact> getChildren();

} // Artifact
