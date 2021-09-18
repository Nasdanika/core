/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Flow#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.flow.Flow#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.Flow#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.Flow#getRoot <em>Root</em>}</li>
 *   <li>{@link org.nasdanika.flow.Flow#getAllElements <em>All Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlow()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='final abstract elements'"
 * @generated
 */
public interface Flow extends Activity {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements of this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<FlowElement> getElements();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Flow}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Flow#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_Extends()
	 * @see org.nasdanika.flow.Flow#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	EList<Flow> getExtends();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Flow}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Flow#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journeys extending this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extensions</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_Extensions()
	 * @see org.nasdanika.flow.Flow#getExtends
	 * @model opposite="extends" changeable="false" derived="true"
	 * @generated
	 */
	EList<Flow> getExtensions();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Root of this journey inheritance hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Root</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_Root()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	Flow getRoot();

	/**
	 * Returns the value of the '<em><b>All Elements</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Own and inherited elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>All Elements</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_AllElements()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement> getAllElements();

} // Flow
