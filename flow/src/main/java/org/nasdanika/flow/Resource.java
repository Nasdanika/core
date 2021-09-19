/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Resource#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends NamedElement {

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Activity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_Services()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity> getServices();
} // Resource
