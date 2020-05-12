/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Engineered Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for elements which have an owning engineer and may contain issues.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.EngineeredElement#getOwners <em>Owners</em>}</li>
 *   <li>{@link org.nasdanika.rigel.EngineeredElement#getIssues <em>Issues</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getEngineeredElement()
 * @model interface="true" abstract="true"
 *        annotation="urn:org.nasdanika label_ru='?\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u043c\u044b\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442' Documentation_ru='\u0411\u0430\u0437\u043e\u0432\u044b\u0439 \u043a\u043b\u0430\u0441\u0441 \u0434\u043b\u044f \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438, \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0435\u043c \u043a\u043e\u0442\u043e\u0440\u043e\u0433\u043e \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \u041c\u043e\u0436\u0435\u0442 \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0442\u044c \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u043c\u044b/\u0437\u0430\u0434\u0430\u0447\u0438.'"
 * @generated
 */
public interface EngineeredElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Owners</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Engineer}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Engineer#getOwns <em>Owns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineer responsible for this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owners</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getEngineeredElement_Owners()
	 * @see org.nasdanika.rigel.Engineer#getOwns
	 * @model opposite="owns"
	 *        annotation="urn:org.nasdanika label_ru='\u0418\u043d\u0436\u0435\u043d\u0435\u0440' Documentation_ru='\u0418\u043d\u0436\u0435\u043d\u0435\u0440, \u0432\u043b\u0430\u0434\u0435\u043b\u0435\u0446 \u044d\u0442\u043e\u0433\u043e \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430.'"
	 * @generated
	 */
	EList<Engineer> getOwners();

	/**
	 * Returns the value of the '<em><b>Issues</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issues associated with the element - problems/pain points, improvement opportunities/enhancements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Issues</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getEngineeredElement_Issues()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika label_ru='\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b' Documentation_ru='\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c - \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b / \u0431\u043e\u043b\u0435\u0432\u044b\u0435 \u0442\u043e\u0447\u043a\u0438, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f / \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f.'"
	 * @generated
	 */
	EList<Issue> getIssues();

} // EngineeredElement
