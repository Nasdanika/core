/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Block#getTry <em>Try</em>}</li>
 *   <li>{@link org.nasdanika.exec.Block#getCatch <em>Catch</em>}</li>
 *   <li>{@link org.nasdanika.exec.Block#getFinally <em>Finally</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getBlock()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/block.md'"
 * @generated
 */
public interface Block extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Try</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Try</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getBlock_Try()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<EObject> getTry();

	/**
	 * Returns the value of the '<em><b>Catch</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catch</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getBlock_Catch()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getCatch();

	/**
	 * Returns the value of the '<em><b>Finally</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finally</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getBlock_Finally()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getFinally();

} // Block
