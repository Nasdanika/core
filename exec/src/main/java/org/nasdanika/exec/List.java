/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.List#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getList()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/list.md'"
 * @generated
 */
public interface List extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getList_Elements()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<EObject> getElements();

} // List
