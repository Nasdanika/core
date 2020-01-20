/**
 */
package org.nasdanika.sirius.tree;

import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adapter Factory Tree</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.sirius.tree.AdapterFactoryTree#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.sirius.tree.TreePackage#getAdapterFactoryTree()
 * @model
 * @generated
 */
public interface AdapterFactoryTree extends DRepresentation, DSemanticDecorator {

	/**
	 * Returns the value of the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' reference.
	 * @see #setDescription(AdapterFactoryTreeDescription)
	 * @see org.nasdanika.sirius.tree.TreePackage#getAdapterFactoryTree_Description()
	 * @model
	 * @generated
	 */
	AdapterFactoryTreeDescription getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.sirius.tree.AdapterFactoryTree#getDescription <em>Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(AdapterFactoryTreeDescription value);
} // AdapterFactoryTree
