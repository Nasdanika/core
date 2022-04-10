/**
 */
package org.nasdanika.diagram;

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
 *   <li>{@link org.nasdanika.diagram.Connection#getThickness <em>Thickness</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getConnection()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/connection.md'"
 * @generated
 */
public interface Connection extends Style {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection target.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Thickness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection thickness.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Thickness</em>' attribute.
	 * @see #setThickness(int)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Thickness()
	 * @model
	 * @generated
	 */
	int getThickness();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#getThickness <em>Thickness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thickness</em>' attribute.
	 * @see #getThickness()
	 * @generated
	 */
	void setThickness(int value);

} // Connection
