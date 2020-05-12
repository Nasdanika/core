/**
 */
package org.nasdanika.rigel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generic relationship between model elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Association#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getAssociation()
 * @model annotation="urn:org.nasdanika label_ru='\u0410\u0441\u0441\u043e\u0446\u0438\u0430\u0446\u0438\u044f' documentation_ru='\u041e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u044f \u043d\u0430\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f \u043c\u0435\u0436\u0434\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u043c\u0438 \u043c\u043e\u0434\u0435\u043b\u0438'"
 * @generated
 */
public interface Association extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(PackageElement)
	 * @see org.nasdanika.rigel.RigelPackage#getAssociation_Target()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika label_ru='\u0426\u0435\u043b\u044c'"
	 * @generated
	 */
	PackageElement getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Association#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(PackageElement value);

} // Association
