/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Note</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.Note#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Note#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Note#getPlacement <em>Placement</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getNote()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/note.md'"
 * @generated
 */
public interface Note extends EObject {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Note text. Can be empty for notes where text is supplied by the ``content`` reference. If not empty  - precedes the content elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getNote_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Note#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite note content to support having links. Elements shall be of type Link of Exec.content.Text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getNote_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContent();

	/**
	 * Returns the value of the '<em><b>Placement</b></em>' attribute.
	 * The default value is <code>"Right"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.diagram.NotePlacement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Note placement for diagram elements. Ignored for connections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Placement</em>' attribute.
	 * @see org.nasdanika.diagram.NotePlacement
	 * @see #setPlacement(NotePlacement)
	 * @see org.nasdanika.diagram.DiagramPackage#getNote_Placement()
	 * @model default="Right"
	 * @generated
	 */
	NotePlacement getPlacement();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Note#getPlacement <em>Placement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Placement</em>' attribute.
	 * @see org.nasdanika.diagram.NotePlacement
	 * @see #getPlacement()
	 * @generated
	 */
	void setPlacement(NotePlacement value);

} // Note
