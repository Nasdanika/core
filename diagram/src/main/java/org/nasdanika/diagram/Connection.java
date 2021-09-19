/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;
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
 *   <li>{@link org.nasdanika.diagram.Connection#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#isBold <em>Bold</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Connection#getThickness <em>Thickness</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"-${style}->"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection type. E.g. ``-->`` or ``..>``. To support styling connection type shall contain ``${style}`` token which will be expanded during generation. E.g. ``-${style}->``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Type()
	 * @model default="-${style}-&gt;"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite connection description to support having links in the description. Elements shall be of type Link of Exec.content.Text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Description()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getDescription();

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection color. E.g. ``lightblue`` or ``DDDDDD``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Dashed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dashed connection style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dashed</em>' attribute.
	 * @see #setDashed(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Dashed()
	 * @model
	 * @generated
	 */
	boolean isDashed();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#isDashed <em>Dashed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dashed</em>' attribute.
	 * @see #isDashed()
	 * @generated
	 */
	void setDashed(boolean value);

	/**
	 * Returns the value of the '<em><b>Dotted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dotted connection style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dotted</em>' attribute.
	 * @see #setDotted(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Dotted()
	 * @model
	 * @generated
	 */
	boolean isDotted();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#isDotted <em>Dotted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dotted</em>' attribute.
	 * @see #isDotted()
	 * @generated
	 */
	void setDotted(boolean value);

	/**
	 * Returns the value of the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bold connection style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bold</em>' attribute.
	 * @see #setBold(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getConnection_Bold()
	 * @model
	 * @generated
	 */
	boolean isBold();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Connection#isBold <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bold</em>' attribute.
	 * @see #isBold()
	 * @generated
	 */
	void setBold(boolean value);

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
