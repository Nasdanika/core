/**
 */
package org.nasdanika.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getGradient <em>Gradient</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#getBorder <em>Border</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.DiagramElement#isBold <em>Bold</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/diagram-element.md'"
 * @generated
 */
public interface DiagramElement extends Link {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element type. E.g. ``state``, ``class``, ``component``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite element name to support having links in the name. Elements shall be of type Link of Exec.content.Text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Name()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getName();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.diagram.DiagramElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nested diagram elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<DiagramElement> getElements();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.diagram.Connection}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outbound connections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Connections()
	 * @model containment="true"
	 * @generated
	 */
	EList<Connection> getConnections();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite element description to support having links in the description. Elements shall be of type Link of Exec.content.Text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Description()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getDescription();

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element stereotype. E.g. ``choice`` or ``fork``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stereotype</em>' attribute.
	 * @see #setStereotype(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Stereotype()
	 * @model
	 * @generated
	 */
	String getStereotype();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getStereotype <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' attribute.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element color. E.g. ``lightblue`` or ``DDDDDD``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Gradient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gradient color - if set the node is displayed with a gradient from ``color`` to ``gradient``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gradient</em>' attribute.
	 * @see #setGradient(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Gradient()
	 * @model
	 * @generated
	 */
	String getGradient();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getGradient <em>Gradient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gradient</em>' attribute.
	 * @see #getGradient()
	 * @generated
	 */
	void setGradient(String value);

	/**
	 * Returns the value of the '<em><b>Border</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Border color. E.g. ``lightblue`` or ``DDDDDD``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Border</em>' attribute.
	 * @see #setBorder(String)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Border()
	 * @model
	 * @generated
	 */
	String getBorder();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#getBorder <em>Border</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Border</em>' attribute.
	 * @see #getBorder()
	 * @generated
	 */
	void setBorder(String value);

	/**
	 * Returns the value of the '<em><b>Dashed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dashed border style.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dashed</em>' attribute.
	 * @see #setDashed(boolean)
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Dashed()
	 * @model
	 * @generated
	 */
	boolean isDashed();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#isDashed <em>Dashed</em>}' attribute.
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
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Dotted()
	 * @model
	 * @generated
	 */
	boolean isDotted();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#isDotted <em>Dotted</em>}' attribute.
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
	 * @see org.nasdanika.diagram.DiagramPackage#getDiagramElement_Bold()
	 * @model
	 * @generated
	 */
	boolean isBold();

	/**
	 * Sets the value of the '{@link org.nasdanika.diagram.DiagramElement#isBold <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bold</em>' attribute.
	 * @see #isBold()
	 * @generated
	 */
	void setBold(boolean value);

} // DiagramElement
