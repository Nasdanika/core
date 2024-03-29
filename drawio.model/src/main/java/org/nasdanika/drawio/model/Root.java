/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Root#getLayers <em>Layers</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Layer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layers</em>' containment reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getRoot_Layers()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<Layer> getLayers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ModelElement getModelElementById(String modelElementId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ModelElement getModelElementByProperty(String name, String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<ModelElement> getModelElementsByProperty(String name, String value);
} // Root
