/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Point#getX <em>X</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Point#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getPoint()
 * @model
 * @generated
 */
public interface Point extends EObject {
	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(Double)
	 * @see org.nasdanika.drawio.model.ModelPackage#getPoint_X()
	 * @model
	 * @generated
	 */
	Double getX();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Point#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(Double value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(Double)
	 * @see org.nasdanika.drawio.model.ModelPackage#getPoint_Y()
	 * @model
	 * @generated
	 */
	Double getY();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Point#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(Double value);

} // Point
