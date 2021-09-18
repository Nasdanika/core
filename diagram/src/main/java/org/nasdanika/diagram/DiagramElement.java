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

} // DiagramElement
