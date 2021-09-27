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
 *   <li>{@link org.nasdanika.flow.Flow#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlow()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='final abstract elements'"
 * @generated
 */
public interface Flow extends Activity<Flow> {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
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
	EList<FlowElement<?>> getElements();

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

} // Flow
