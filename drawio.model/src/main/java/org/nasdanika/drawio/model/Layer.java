/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Layer#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getLayer()
 * @model
 * @generated
 */
public interface Layer extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.LayerElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getLayer_Elements()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<LayerElement> getElements();

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
} // Layer
