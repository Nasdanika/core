/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.Diagram#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#isVertical <em>Vertical</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#isHideEmptyDescription <em>Hide Empty Description</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getDiagram()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/diagram.md'"
 * @generated
 */
public interface Diagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.diagram.DiagramElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<DiagramElement> getElements();

	/**
	 * Returns the value of the '<em><b>Vertical</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertical</em>' attribute.
	 * @see #setVertical(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Vertical()
	 * @model default="true"
	 * @generated
	 */
	boolean isVertical();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#isVertical <em>Vertical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertical</em>' attribute.
	 * @see #isVertical()
	 * @generated
	 */
	void setVertical(boolean value);

	/**
	 * Returns the value of the '<em><b>Hide Empty Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hide Empty Description</em>' attribute.
	 * @see #setHideEmptyDescription(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_HideEmptyDescription()
	 * @model
	 * @generated
	 */
	boolean isHideEmptyDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#isHideEmptyDescription <em>Hide Empty Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hide Empty Description</em>' attribute.
	 * @see #isHideEmptyDescription()
	 * @generated
	 */
	void setHideEmptyDescription(boolean value);

} // Diagram
