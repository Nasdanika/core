/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Engineer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Engineers own engineered elements and are assigned issues associated with these elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Engineer#getOwns <em>Owns</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Engineer#getAssignments <em>Assignments</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getEngineer()
 * @model annotation="urn:org.nasdanika label_ru='\u0418\u043d\u0436\u0435\u043d\u0435\u0440' documentation_ru='\u0421\u043f\u0435\u0446\u0438\u0430\u043b\u0438\u0441\u0442, \u0432\u043b\u0430\u0434\u0435\u044e\u0449\u0438\u0439 \u043a\u0430\u043a\u0438\u043c-\u043b\u0438\u0431\u043e \u0440\u0435\u0441\u0443\u0440\u0441\u043e\u043c, \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u043c \u0438\u043b\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u0430 \u0438 \u043f\u043e\u043b\u0443\u0447\u0430\u044e\u0449\u0438\u0439 \u0437\u0430\u0434\u0430\u0447\u0438, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u0442\u0438\u043c \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u043c \u0438\u043b\u0438 \u0440\u0435\u0441\u0443\u0440\u043e\u043c.'"
 * @generated
 */
public interface Engineer extends PackageElement {
	/**
	 * Returns the value of the '<em><b>Owns</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.EngineeredElement}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.EngineeredElement#getOwners <em>Owners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements which this engineer owns. Ownership is propagated down to child elements recursively, unless these elements have an explicitly assigned owner. Also issues associated with owned elements and not having an engineer explicitly assigned to them are implicitly assigned to the element owner.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owns</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getEngineer_Owns()
	 * @see org.nasdanika.rigel.EngineeredElement#getOwners
	 * @model opposite="owners"
	 *        annotation="urn:org.nasdanika label_ru='\u0425\u043e\u0437\u044f\u0439\u0441\u0442\u0432\u043e' documentation_ru='\u0421\u043e\u0432\u043e\u043a\u0443\u043f\u043d\u043e\u0441\u0442\u044c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432, \u043a\u043e\u0442\u043e\u0440\u044b\u043c\u0438 \u0432\u043b\u0430\u0434\u0435\u0435\u0442 \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \n\u0412\u043b\u0430\u0434\u0435\u043d\u0438\u0435 \u0440\u0430\u0441\u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u044f\u0435\u0442\u0441\u044f \u0434\u043e \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0440\u0435\u043a\u0443\u0440\u0441\u0438\u0432\u043d\u043e, \u0435\u0441\u043b\u0438 \u0442\u043e\u043b\u044c\u043a\u043e \u044d\u0442\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u044b \u043d\u0435 \u0438\u043c\u0435\u044e\u0442 \u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0435\u043d\u043d\u043e\u0433\u043e \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0430. \u0422\u0430\u043a\u0436\u0435 \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u0441\u043e\u0431\u0441\u0442\u0432\u0435\u043d\u043d\u044b\u043c\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u043c\u0438 \u0438 \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0438\u0435\u043c \u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0435\u043d\u043d\u043e\u0433\u043e \u0438\u043c \u0438\u043d\u0436\u0435\u043d\u0435\u0440\u0430, \u043d\u0435\u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0430\u044e\u0442\u0441\u044f \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430.'"
	 * @generated
	 */
	EList<EngineeredElement> getOwns();

	/**
	 * Returns the value of the '<em><b>Assignments</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Issue}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Issue#getAssignedTo <em>Assigned To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineer's assigned issues.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assignments</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getEngineer_Assignments()
	 * @see org.nasdanika.rigel.Issue#getAssignedTo
	 * @model opposite="assignedTo"
	 *        annotation="urn:org.nasdanika label_ru='\u0417\u0430\u0434\u0430\u0447\u0430' documentation_ru='\u0417\u0430\u0434\u0430\u0447\u0438, \u043f\u0435\u0440\u0435\u0434\u0430\u043d\u043d\u044b\u0435 \u0438\u043d\u0436\u0435\u043d\u0435\u0440\u0443 \u043d\u0430 \u0438\u0441\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435'"
	 * @generated
	 */
	EList<Issue> getAssignments();

} // Engineer
