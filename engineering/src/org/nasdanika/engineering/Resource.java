/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something leveraged by an actor to perform an activity. For example - a tool.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Resource#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Resource#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getResource()
 * @model annotation="urn:org.nasdanika label_ru='\u0420\u0435\u0441\u0443\u0440\u0441' documentation_ru='\u0420\u0435\u0441\u0443\u0440\u0441 - \u043e\u0431\u044a\u0435\u043a\u0442, \u043f\u043e\u0437\u0432\u043e\u043b\u044f\u044e\u0449\u0438\u0439 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044e \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0442\u044c \u0440\u0430\u0431\u043e\u0442\u0443. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u043a\u0430\u043a\u043e\u0439 \u043b\u0438\u0431\u043e \u0438\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442'"
 * @generated
 */
public interface Resource extends PackageElement, EngineeredElement, Capability {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource can be nested. E.g. a virtual machine may host a web server.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getResource_Children()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0412\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u0435 \u0440\u0435\u0441\u0443\u0440\u0441\u044b' documentation_ru='\u0420\u0435\u0441\u0443\u0440\u0441\u044b \u043c\u043e\u0433\u0443\u0442 \u0431\u044b\u0442\u044c \u0432\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u043c\u0438, \u043c\u043d\u043e\u0433\u043e\u043a\u043e\u043c\u043f\u043e\u043d\u0435\u043d\u0442\u043d\u044b\u043c\u0438. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u0432\u0438\u0440\u0442\u0443\u0430\u043b\u044c\u043d\u0430\u044f \u043c\u0430\u0448\u0438\u043d\u0430 \u043c\u043e\u0436\u0435\u0442 \u0432\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0432 \u0441\u0435\u0431\u044f \u0432\u0435\u0431-\u0441\u0435\u0440\u0432\u0435\u0440.'"
	 * @generated
	 */
	EList<Resource> getChildren();

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resources may host artifacts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getResource_Artifacts()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442' documentation_ru='\u0420\u0435\u0441\u0443\u0440\u0441\u044b \u043c\u043e\u0433\u0443\u0442 \u0432\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0432 \u0441\u0435\u0431\u044f \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b'"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

} // Resource
