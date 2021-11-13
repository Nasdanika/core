/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.List#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getList()
 * @model
 * @generated
 */
public interface List extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getList_Value()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika reference-type='map: Map\nlist: List\nstring: String' value-feature='true'"
	 * @generated
	 */
	EList<EObject> getValue();

} // List
