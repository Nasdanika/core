/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.nasdanika.ncore.NamedElement;

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
 *   <li>{@link org.nasdanika.diagram.Diagram#isHideFootbox <em>Hide Footbox</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getContext <em>Context</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getDepth <em>Depth</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getDiagram()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/diagram.md'"
 * @generated
 */
public interface Diagram extends NamedElement {
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
	 * <!-- begin-model-doc -->
	 * Hides empty descriptions on state diagrams.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Hide Footbox</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hides footbox on sequence diagrams.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hide Footbox</em>' attribute.
	 * @see #setHideFootbox(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_HideFootbox()
	 * @model default="true"
	 * @generated
	 */
	boolean isHideFootbox();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#isHideFootbox <em>Hide Footbox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hide Footbox</em>' attribute.
	 * @see #isHideFootbox()
	 * @generated
	 */
	void setHideFootbox(boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"plantuml:uml"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Type()
	 * @model default="plantuml:uml"
	 *        annotation="urn:org.nasdanika documentation-reference='doc/diagram-type.md'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#getType <em>Type</em>}' attribute.
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
	 * Diagram notes (legend).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Notes()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Note> getNotes();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' map.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Properties()
	 * @model mapType="org.nasdanika.diagram.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 *        annotation="urn:org.nasdanika documentation-reference='doc/diagram-properties.md'"
	 * @generated
	 */
	EMap<String, String> getProperties();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Depth of context elements to show on the diagram. Default is 0. -1 means no limit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Context</em>' attribute.
	 * @see #setContext(int)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Context()
	 * @model default="0"
	 * @generated
	 */
	int getContext();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#getContext <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(int value);

	/**
	 * Returns the value of the '<em><b>Depth</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Depth of the diagram partition elements. Default is -1 (no limit). Once the depth is reached content of partitions is not shown.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Depth</em>' attribute.
	 * @see #setDepth(int)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Depth()
	 * @model default="-1"
	 * @generated
	 */
	int getDepth();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.Diagram#getDepth <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depth</em>' attribute.
	 * @see #getDepth()
	 * @generated
	 */
	void setDepth(int value);

} // Diagram