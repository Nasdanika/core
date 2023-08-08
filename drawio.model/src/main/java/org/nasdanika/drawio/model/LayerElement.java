/**
 */
package org.nasdanika.drawio.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layer Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.LayerElement#getGeometry <em>Geometry</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getLayerElement()
 * @model
 * @generated
 */
public interface LayerElement extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geometry</em>' containment reference.
	 * @see #setGeometry(Geometry)
	 * @see org.nasdanika.drawio.model.ModelPackage#getLayerElement_Geometry()
	 * @model containment="true"
	 * @generated
	 */
	Geometry getGeometry();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.LayerElement#getGeometry <em>Geometry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geometry</em>' containment reference.
	 * @see #getGeometry()
	 * @generated
	 */
	void setGeometry(Geometry value);
} // LayerElement
