/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model element which can be contained by a package.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.PackageElement#getAssociations <em>Associations</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getPackageElement()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika label_ru='\u0423\u043f\u0430\u043a\u043e\u0432\u0430\u043d\u043d\u044b\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442' Documentation_ru='\u042d\u043b\u0435\u043c\u0435\u043d\u0442 \u043c\u043e\u0434\u0435\u043b\u0438, \u043a\u043e\u0442\u0440\u044b\u0439 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u0432\u043a\u043b\u044e\u0447\u0435\u043d \u0432 \u043f\u0430\u043a\u0435\u0442'"
 * @generated
 */
public interface PackageElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Associations</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Association}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getPackageElement_Associations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Association> getAssociations();

} // PackageElement
