/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.Style#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Style#isBold <em>Bold</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getStyle()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/style.md'"
 * @generated
 */
public interface Style extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection type. E.g. ``-->`` or ``..>``. To support styling connection type shall contain ``${style}`` token which will be expanded during generation. E.g. ``-${style}->``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Style#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.diagram.Note}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element notes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Notes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Note> getNotes();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite connection description to support having links in the description. Elements shall be of type Link of Exec.content.Text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Description()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getDescription();

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element color. E.g. ``lightblue`` or ``DDDDDD``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Style#getColor <em>Color</em>}' attribute.
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
	 * Dashed border style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dashed</em>' attribute.
	 * @see #setDashed(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Dashed()
	 * @model
	 * @generated
	 */
	boolean isDashed();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Style#isDashed <em>Dashed</em>}' attribute.
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
	 * Dotted border style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dotted</em>' attribute.
	 * @see #setDotted(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Dotted()
	 * @model
	 * @generated
	 */
	boolean isDotted();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Style#isDotted <em>Dotted</em>}' attribute.
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
	 * Bold border style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bold</em>' attribute.
	 * @see #setBold(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getStyle_Bold()
	 * @model
	 * @generated
	 */
	boolean isBold();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Style#isBold <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bold</em>' attribute.
	 * @see #isBold()
	 * @generated
	 */
	void setBold(boolean value);

} // Style
