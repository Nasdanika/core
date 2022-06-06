/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.Diagram#isVertical <em>Vertical</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#isHideEmptyDescription <em>Hide Empty Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#isHideFootbox <em>Hide Footbox</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getContext <em>Context</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getDepth <em>Depth</em>}</li>
 *   <li>{@link org.nasdanika.diagram.Diagram#getLayers <em>Layers</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getDiagram()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/diagram.md'"
 * @generated
 */
public interface Diagram extends Layer {
	/**
	 * Returns the value of the '<em><b>Vertical</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Diagram direction - top-down if true (default), left to right if false.
	 * <!-- end-model-doc -->
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
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Properties()
	 * @model containment="true" keys="name"
	 *        annotation="urn:org.nasdanika documentation-reference='doc/diagram-properties.md' reference-type='map: \n  ns-uri: urn:org.nasdanika.ncore\n  name: MapProperty\nlist:\n  ns-uri: urn:org.nasdanika.ncore\n  name: ListProperty\nstring:\n  ns-uri: urn:org.nasdanika.ncore\n  name: StringProperty\n' value-feature='value'"
	 * @generated
	 */
	EList<Property> getProperties();

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

	/**
	 * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.diagram.Layer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Diagram elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Layers</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagram_Layers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Layer> getLayers();

} // Diagram
