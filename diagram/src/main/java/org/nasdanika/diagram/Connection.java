/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.Connection#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getConnection()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/connection.md'"
 * @generated
 */
public interface Connection extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(DiagramElement)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Target()
	 * @model
	 * @generated
	 */
	DiagramElement getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(DiagramElement value);

} // Connection
